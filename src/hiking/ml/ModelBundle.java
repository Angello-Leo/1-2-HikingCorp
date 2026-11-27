package hiking.ml;

public final class ModelBundle {
    public final Regressor model; public final Evaluation eval;
    public ModelBundle(Regressor m, Evaluation e) { model = m; eval = e; }
    public String label() { return model.type().equals("SLR") ? "Simple Linear Regression" : "Random Forest Regression"; }
}
