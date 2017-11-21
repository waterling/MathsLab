package lab.fourth.algorithms;


public class PredictorAndCorrector extends OdeStrategy {
    @Override
    public void calc(double xStart, double xFinish, double step, double yZero, double yFirst) {
        xCoordinates.add(xStart);
        yCoordinates.add(yZero);

        for (int i = 1; xFinish >= xStart; i++) {
            xStart += step;
            xCoordinates.add(xStart);
            yCoordinates.add(yCoordinates.get(i - 1) + step *
                    function(xCoordinates.get(i - 1) + step / 2,
                            yCoordinates.get(i - 1) + step / 2 *
                                    function(xCoordinates.get(i - 1),
                                    yCoordinates.get(i - 1))));
        }
    }

    private double function(double x, double y) {
        return 2*(x*x+y);
    }
}
