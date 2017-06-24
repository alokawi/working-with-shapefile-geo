/**
 * 
 */
package alokawi.shapefilegeo.core;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.opengis.geometry.Envelope;

import com.vividsolutions.jts.awt.PointShapeFactory.Point;
import com.vividsolutions.jts.awt.ShapeReader;
import com.vividsolutions.jts.geom.Polygon;

/**
 * @author alok
 *
 */
public class ShapefileGeoExample {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ShapefileGeoExample shapefileGeoExample = new ShapefileGeoExample();
		shapefileGeoExample.run();
	}

	private void run() throws FileNotFoundException, IOException {
		final File file = new File("cntry06.shp");
		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			final Envelope envelope = new Envelope();
			final Polygon polygon = new Polygon();

			final ShapeReader shpReader = new ShapeReader(
					new DataInputStream(new BufferedInputStream(fileInputStream)));
			while (shpReader.hasMore()) {
				shpReader.queryPolygon(polygon);
				polygon.queryEnvelope(envelope);
				final Point center = envelope.getCenter();
				System.out.format("%.6f %.6f%n", center.getX(), center.getY());
			}
		} finally {

		}
	}

}
