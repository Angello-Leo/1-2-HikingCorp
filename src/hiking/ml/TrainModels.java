package hiking.ml;

/** Command-line trainer:  java -cp build/classes hiking.ml.TrainModels   (run from the project folder). */
public final class TrainModels {
    public static void main(String[] args) throws Exception {
        long t0 = System.currentTimeMillis();
        AnalysisService.get().retrainAll((m, f) -> System.out.printf("[%3d%%] %s%n", (int) (f * 100), m));
        for (Task t : Task.values()) {
            for (String ty : new String[]{AnalysisService.SLR, AnalysisService.RF}) {
                Evaluation e = AnalysisService.get().bundle(t, ty).eval;
                System.out.printf("%-34s %-3s  test: MAE=%.2f RMSE=%.2f R2=%.3f WAPE=%.1f%% | cv R2=%.3f%s%n", t.title, ty, e.test.mae, e.test.rmse, e.test.r2, e.test.wape, e.cvR2,
                        e.cls == null ? "" : String.format(" | acc=%.2f f1=%.2f thr=%.2f", e.cls.accuracy, e.cls.f1, e.classThreshold));
            }
        }
        System.out.println("Done in " + (System.currentTimeMillis() - t0) / 1000.0 + " s");
    }
}
