import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Sculpture extends Painting {

	private int weight;

	public Sculpture(String fileName) {
		super(fileName);
		File f = new File(fileName);
		try (BufferedReader b = new BufferedReader(new FileReader(f))) {
			for (int i = 0; i < 4; i++) {
				b.readLine();
			}
			this.weight = Integer.parseInt(b.readLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return super.toString() + "\nWeight: " + weight + "kg";
	}

	public boolean isSculpture() {
		return true;
	}
}
