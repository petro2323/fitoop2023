import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Painting {
	private String title;
	private String artistName;
	private double price;
	private String dimensions;

	public boolean isValidDimensionFormat(String dimensions) {
		String[] parts = dimensions.split("x");

		if (parts.length != 2) {
			return false;
		}

		try {
			int width = Integer.parseInt(parts[0].trim());
			int height = Integer.parseInt(parts[1].trim());
			return width > 0 && height > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void setDimensions(String dimensions) throws DimensionException {
		if (!isValidDimensionFormat(dimensions)) {
			throw new DimensionException("Invalid format!");
		}
		this.dimensions = dimensions;
	}

	public Painting(String fileName) {
		File f = new File(fileName);
		try (BufferedReader b = new BufferedReader(new FileReader(f))) {
			this.title = b.readLine();
			this.artistName = b.readLine();
			this.price = Double.parseDouble(b.readLine());
			setDimensions(b.readLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return title;
	}

	public String getArtistName() {
		return artistName;
	}

	public double getPrice() {
		return price;
	}

	public String getDimensions() {
		return dimensions;
	}

	@Override
	public String toString() {
		return "\nArt title: " + title + "\nArtist name: " + artistName + "\nPrice: " + price + "e\nDimensions: "
				+ dimensions;
	}

	public boolean isSculpture() {
		return false;
	}
}
