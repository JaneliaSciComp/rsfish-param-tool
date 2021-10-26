import bdv.util.BdvFunctions;
import bdv.util.BdvOptions;
import bdv.util.BdvStackSource;
import net.imglib2.FinalInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.ARGBType;
import net.imglib2.util.Intervals;
import net.imglib2.view.IntervalView;
import net.imglib2.view.Views;
import org.janelia.saalfeldlab.n5.N5FSReader;
import org.janelia.saalfeldlab.n5.imglib2.N5Utils;

import java.io.IOException;
import java.util.Arrays;

public class ViewN5 {

    public static void main(String[] args) throws IOException {

        String path = "/Users/rokickik/Desktop/export.n5";
        String dataSet = "c0/s0";

        N5FSReader n5FSReader = new N5FSReader(path);
        Img<?> img = N5Utils.open(n5FSReader, dataSet);

        System.out.println("intervals: " + Intervals.toString(img));

        FinalInterval minMax = Intervals.createMinMax(100, 100, 100, 200, 200, 150);
        IntervalView view = Views.zeroMin(Views.interval(img, minMax));

        FinalInterval minMax2 = Intervals.createMinMax(250, 250, 100, 350, 350, 150);
        IntervalView view2 = Views.zeroMin(Views.interval(img, minMax2));

        RandomAccessibleInterval stack = Views.stack(view, view2);

        BdvStackSource<?> bdv = BdvFunctions.show(stack, "");
        bdv.setDisplayRange(0, 1024);

//        BdvStackSource<?> bdv = BdvFunctions.show(img, "");
//        bdv.setDisplayRange(0, 1024);
//
//        BdvStackSource<?> bdv2 = BdvFunctions.show(view, "", new BdvOptions().addTo(bdv));
//        bdv2.setDisplayRange(0, 1024);
//        bdv2.setColor(new ARGBType(0x00ff00));


        //BdvFunctions.showPoints()

    }
}
