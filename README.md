# 1/2 HI KING CORP - NetBeans (Ant) edition

Java Application project (Ant) for **Apache NetBeans**, Swing **JFrame / JPanel / JDialog forms** that open in the **Design tab**.
Database: MySQL. Prediction: Simple Linear Regression + Random Forest Regression (pure Java, saved models in `models/`).

## 1. Open the project
1. Install a JDK 11+ and Apache NetBeans.
2. File > Open Project > select this folder (`HiKingCorp`, the icon shows it is a NetBeans project).
   NetBeans creates `nbproject/build-impl.xml` itself the first time - this is normal.
3. If NetBeans asks which JDK to use, pick JDK 11 or newer (Project Properties > Libraries > Java Platform).

## 2. MySQL
1. Install MySQL 8 (or XAMPP/WAMP) and start the server. Default login used: host `localhost`, port `3306`, user `root`, empty password.
2. Download **mysql-connector-j** (Platform Independent, ZIP) from https://dev.mysql.com/downloads/connector/j/ and copy the
   `mysql-connector-j-8.x.x.jar` file into this project's **`lib`** folder. (Nothing else to configure - the app finds it there.
   Alternatively add it in Project > Libraries > Add JAR.)
3. Run the project (F6). The first start creates the database `hiking_db`, tables, demo accounts, 30 menu items and ~90 days of sales.
   If MySQL needs another user/password, the app shows a connection screen where you can enter them.

## 3. Accounts (change the passwords after the first login)
| Level | Username | Password | Access |
|---|---|---|---|
| Administrator | admin | Admin@123 | Dashboard, predictions (inputs + results), users, audit log |
| Instructor | instructor | Instructor@123 | Dashboard + full analysis (data sets, metrics, descriptions, retrain) |
| Inventory clerk | clerk | Clerk@123 | Inventory, stock in / wastage, inventory dashboard |
| Sales personnel | sales | Sales@123 | New sale (POS), own sales history, own dashboard |
| Staff | *(self sign-up)* | - | View-only stock dashboard + My Account. Everyone who signs up starts here |

**Sign-up:** on the login window click *New here? Create an account*. New accounts are always **Staff**.
**Changing levels:** an Administrator opens *User Management > select a user > Edit* and picks any level (promote Staff to Inventory Clerk, demote a clerk back to Staff, etc.), disables, or **deletes** the account (Delete user button). Deleting is refused for yourself, the last administrator, and users who already have sales or stock records (disable those instead). Admins cannot change or disable their own account, and the last active administrator is protected.

## 4. Editing the design (Design tab)
Open any form in `src/hiking/ui` (double-click the `.java`, press **Design**). Everything you see there - labels, buttons, text boxes,
tables, panels, colours, fonts, sizes - can be selected and changed in the Properties window, and buttons wired with right-click > Events.

| Form | What it is |
|---|---|
| `LoginFrame` | Sign-in window |
| `SignupFrame` | Create-an-account window (same size as sign-in) |
| `DbSetupDialog` | MySQL connection dialog |
| `MainFrame` | Main window: sidebar menu, header, page area (one button per menu entry) |
| `DashboardPanel`, `InventoryPanel`, `PosPanel`, `SalesHistoryPanel`, `UsersPanel`, `AuditPanel`, `AccountPanel`, `AnalysisPanel` | The pages |
| `ProductDialog`, `StockDialog`, `UserDialog`, `PasswordDialog`, `ReceiptDialog` | Pop-up forms |

Notes:
- **Look:** the rounded cards, buttons, text boxes, combo boxes, underline tabs and clean tables are applied at run time by `Util.skin(...)` using `Flat.java` (radii and line colours are constants at the top of each class). In the Design tab you will see plain square components; set colours, fonts and sizes there as usual - a white panel with padding becomes a rounded card, and a button with a background colour becomes a rounded button.
- Layouts are Border / Grid / Flow nested panels (easy to rearrange). Page backgrounds use `#F4F6FA`, orange `#E8532B`, dark sidebar `#161A23`.
- Things filled with data at run time (table rows, charts, menu tiles, prediction input fields, evaluation cards) are built in code in each
  form's `postInit()` method - the containers they go into (`pnlXxx`, `tblXxx`) are in the Design tab.
- The shared flat look of text boxes, tables, scroll panes (colours, row height, header style) is applied by `Util.skin(...)` in `Util.java`;
  global colours/fonts are in `Theme.java`.
- Do not edit the grey "Generated Code" blocks by hand; use the Design tab. Your own code goes outside them.
- Roles / permissions: `hiking/model/Role.java` (menu entries are shown per role in `MainFrame.setupNav()`).

## 5. Folders
`src/hiking/{dao,db,ml,model,security,ui}`, `data/` (5 CSV data sets, 1,000+ rows each), `models/` (saved `.hkm` models),
`sql/hiking_db.sql` (optional manual schema), `tools/` (data generator), `config/db.properties` (created on first run).

## 6. Without NetBeans
`run.bat` (Windows) or `./run.sh` compiles and starts the app (needs JDK 11+ and the MySQL jar in `lib/`).


## 7. Look & feel (2026 design)
Palette and shapes follow the new design: orange sidebar/buttons, blue-grey page, beige inputs, slate selected menu item. Colours are constants in `Theme.java`; card/field/button shapes in `Flat.java`; the sign-in / sign-up side panel is drawn by `AuthSide.java` (uses the mountain logo). To use the design's typeface, put its `.ttf` file(s) in a `fonts/` folder next to `data/` - they are picked up automatically; otherwise the first installed rounded font (Fredoka, Quicksand, Nunito...) or Segoe UI / Inter is used.
