import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

public class Gallery {
	private String title;
	private int maxBudget;
	private Painting[] all;

	public Gallery(String title, int maxBudget, int maxPaintings) {
		this.title = title;
		this.maxBudget = maxBudget;
		this.all = new Painting[maxPaintings];
	}

	private double budget() {
		double sum = 0;
		for (int i = 0; i < all.length; i++) {
			if (all[i] != null) {
				sum += all[i].getPrice();
			}
		}
		return sum;
	}

	public boolean addPainting(Painting p, int position) throws BudgetException {
		if (budget() + p.getPrice() > maxBudget) {
			throw new BudgetException("Cannot add " + p.getTitle() + " because of the budget limit.");
		}

		if (all[position] != null) {
			System.out.println("This position is already taken!");
			return false;
		}

		all[position] = p;
		return true;
	}

	public String showGalleryInfo() {
		StringBuffer s = new StringBuffer("Gallery name: " + title + ", Max budget: " + maxBudget + "e\n");
		for (int i = 0; i < all.length; i++) {
			if (all[i] != null) {
				if (all[i].isSculpture()) {
					Sculpture sc = (Sculpture) all[i];
					s.append("\nSculptures:\n");
					s.append(sc.toString());
				} else {
					Painting p = (Painting) all[i];
					s.append("\nPaintings:\n");
					s.append(p.toString());
				}
			}
		}
		return s.toString();
	}

	private int getNumberOfArtworks(String artistName) {
		int num = 0;
		for (int i = 0; i < all.length; i++) {
			if (all[i] != null && all[i].getArtistName().equalsIgnoreCase(artistName)) {
				num++;
			}
		}
		return num;
	}

	public String showMeArtistWithArtsMoreThan(int n) {
		String[] artists = new String[all.length];
		int count = 0;
		for (int i = 0; i < all.length; i++) {
			if (all[i] != null) {
				int artistsWork = getNumberOfArtworks(all[i].getArtistName());
				if (artistsWork > n) {
					artists[count] = all[i].getArtistName();
					count++;
				}
			}
		}
		String[] artistsArray = new String[count];
		System.arraycopy(artists, 0, artistsArray, 0, count);

		return Arrays.toString(artistsArray);
	}

	private int allArts() {
		return numOfPaintings() + numOfSculptures();
	}

	private int numOfSculptures() {
		int sculptures = 0;
		for (int i = 0; i < all.length; i++) {
			if (all[i] != null) {
				if (all[i].isSculpture()) {
					sculptures++;
				}
			}
		}
		return sculptures;
	}

	private int numOfPaintings() {
		int paintings = 0;
		for (int i = 0; i < all.length; i++) {
			if (all[i] != null) {
				if (!all[i].isSculpture()) {
					paintings++;
				}
			}
		}
		return paintings;
	}

	public String countAllOfArtFromArtist(String artistName) {
		int paintings = 0;
		int sculptures = 0;
		for (int i = 0; i < all.length; i++) {
			if (all[i] != null && all[i].getArtistName().equalsIgnoreCase(artistName)) {
				if (all[i].isSculpture()) {
					sculptures++;
				} else {
					paintings++;
				}
			}
		}
		int artistsArts = (paintings + sculptures) * 100 / allArts();

		return artistName + " has " + paintings + " painting/s and " + sculptures
				+ " sculpture/s. The percent value of the sum of all arts is " + (double) artistsArts + "%";
	}

	public String reverseArts(String type) {
		if (type.equalsIgnoreCase("paintings")) {
			Painting[] p = new Painting[all.length];
			int count = 0;

			for (int i = all.length - 1; i >= 0; i--) {
				if (all[i] != null && !all[i].isSculpture()) {
					p[count] = all[i];
					count++;
				}
			}

			Painting[] reversed = new Painting[count];
			for (int i = 0; i < count; i++) {
				reversed[i] = p[i];
			}

			return Arrays.toString(reversed);
		} else if (type.equals("sculptures")) {
			Painting[] p = new Painting[all.length];
			int count = 0;

			for (int i = all.length - 1; i >= 0; i--) {
				if (all[i] != null && all[i].isSculpture()) {
					p[count] = all[i];
					count++;
				}
			}

			Painting[] reversed = new Painting[count];
			for (int i = 0; i < count; i++) {
				reversed[i] = p[i];
			}

			return Arrays.toString(reversed);
		}
		return null;
	}

	public void export(String fileName, int numArts, boolean isSculpture) {
		File f = new File(fileName);
		if (f.exists()) {
			System.out.println("File already exists!");
			return;
		}
		Random r = new Random();
		if (isSculpture) {
			if (numArts > numOfSculptures()) {
				System.out.println("There are less than " + numArts + " sculptures!");
				return;
			} else {
				try (PrintStream p = new PrintStream(f)) {
					String sculptures = reverseArts("sculptures");
					String[] array = sculptures.split(",");
					for (int i = 0; i < numArts; i++) {
						int num = r.nextInt(array.length);
						p.println(array[num]);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			if (numArts > numOfPaintings()) {
				System.out.println("There are less than " + numArts + " paintings!");
				return;
			} else {
				try (PrintStream p = new PrintStream(f)) {
					String paintings = reverseArts("paintings");
					String[] array = paintings.split(",");
					for (int i = 0; i < numArts; i++) {
						int num = r.nextInt(array.length);
						p.println(array[num]);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}