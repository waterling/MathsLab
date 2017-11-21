package lab.third.algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.*;

public class CubicSplineWithLongDistance extends CubicSpline {

    public CubicSplineWithLongDistance() {
        super(9);
    }

    @Override
    protected void initializePoints() {
        double x = 0;
        for (int i = 0; i < 10; i++) {
            x += new BigDecimal(random() * 5 * PI).setScale(2, RoundingMode.UP).doubleValue();
            coordinateX[i] = x;
            coordinateY[i] = new BigDecimal(cos(x)).setScale(2, RoundingMode.UP).doubleValue();
        }

    }
}
