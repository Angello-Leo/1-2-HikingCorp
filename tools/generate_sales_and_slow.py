"""Generates data/sales_dataset.csv (daily sales, 1,200+ rows) and data/slow_moving_dataset.csv (1,300 rows).
The other two datasets (product_demand_dataset.csv, best_selling_dataset.csv) are kept as they are.
Run from the project folder:  python tools/generate_sales_and_slow.py"""
import csv, math, random, datetime, os
random.seed(2026)
ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..")
DATA = os.path.join(ROOT, "data")
HOL = {(1,1),(4,9),(5,1),(6,12),(8,21),(11,1),(11,30),(12,24),(12,25),(12,30),(12,31)}   # same list as hiking.ml.Cal
DOW = [.88,.86,.90,.95,1.15,1.28,1.18]; MON = [.95,.97,1.0,1.04,1.06,.96,.94,.95,.97,1.02,1.05,1.15]
TEMP = [26,27,29,31,32,29,28,28,28,28,27,26]
def is_payday(d): return d.day in (15,30) or (d.month==2 and d.day==28)

# ---------------------------------------------------------------- daily sales
rows=[]; d=datetime.date(2023,7,1); end=datetime.date(2026,10,13); prev=17000.0; ar=0.0
while d<=end:
    hol=int((d.month,d.day) in HOL); pay=int(is_payday(d)); promo=int(random.random()<.18); wk=int(d.weekday()>=5)
    temp=round(random.gauss(TEMP[d.month-1],1.6),1)
    rain=round(random.expovariate(1/9.0),1) if random.random()<(.55 if 6<=d.month<=10 else .15) else 0.0
    rain=min(rain,180.0)
    ar=0.45*ar+random.gauss(0,.05)
    f=DOW[d.weekday()]*MON[d.month-1]*(1.14 if hol else 1)*(1.10 if pay else 1)*(1.20 if promo else 1)*(1-min(rain,80)*0.0016)*(1+0.012*(temp-28))
    f*=1+0.00018*(d-datetime.date(2023,7,1)).days
    sales=round(17000*f*math.exp(ar),2)
    rows.append([d.isoformat(),d.isoweekday(),d.month,wk,hol,pay,promo,temp,rain,round(prev,2),sales])
    prev=sales; d+=datetime.timedelta(days=1)
with open(os.path.join(DATA,"sales_dataset.csv"),"w",newline="") as f:
    w=csv.writer(f); w.writerow(["date","day_of_week","month","is_weekend","is_holiday","is_payday","is_promo","temperature_c","rain_mm","prev_day_sales","total_sales"]); w.writerows(rows)
print("sales_dataset.csv",len(rows))

# ---------------------------------------------------------------- slow moving
CAT={"Burgers":1,"Chicken":2,"Rice Meals":3,"Sides":4,"Beverages":5,"Desserts":6}
menu=list(csv.DictReader(open(os.path.join(DATA,"products_master.csv"))))
out=[]
for i in range(1300):
    m=random.choice(menu); pop=float(m["base_pop"]); price=float(m["unit_price"])*random.uniform(.92,1.12); cost=float(m["cost_price"])
    margin=round((price-cost)/price*100,1)
    level=math.exp(random.gauss(0,.55))                      # how popular the item is at this branch/time
    avg30=round(max(.1,pop*level*random.uniform(.8,1.2)),2)
    promo=int(random.random()<.25); shelf=int(m["shelf_life_days"])
    trend=math.exp(random.gauss(-0.06,.22))                  # items can be fading or rising
    vel=avg30*trend*(1.22 if promo else 1)*(1+0.0035*(margin-50)*0)
    vel=max(0.0,round(vel*random.uniform(.93,1.07),2))
    stock=int(max(0,random.gauss(max(8,avg30*random.uniform(2,8)),avg30*1.5+5)))
    since=int(min(90,max(0,random.expovariate(max(.15,avg30)*0.9)*1.0)))
    out.append([m["name"],CAT[m["category"]],round(price),stock,since,avg30,shelf,promo,margin,vel])
with open(os.path.join(DATA,"slow_moving_dataset.csv"),"w",newline="") as f:
    w=csv.writer(f); w.writerow(["product_name","category_code","unit_price","stock_on_hand","days_since_last_sale","avg_daily_units_30d","shelf_life_days","is_promo","margin_pct","velocity_next_14d"]); w.writerows(out)
print("slow_moving_dataset.csv",len(out))
