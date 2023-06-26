
public class Test {
	public void test() {
		Painting p = new Painting("painting.txt");
		Sculpture s = new Sculpture("sculpture.txt");
		Sculpture s1 = new Sculpture("sculpture2.txt");
		
		Gallery g = new Gallery("Museum of arts", 2000, 5);
		
//		try {
//			g.addPainting(p, 0);
//		} catch (BudgetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			g.addPainting(s, 0);
		} catch (BudgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			g.addPainting(s1, 1);
		} catch (BudgetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(g.showGalleryInfo());
		System.out.println(g.showMeArtistWithArtsMoreThan(0));
		System.out.println(g.countAllOfArtFromArtist("Dualingo Dua"));
		System.out.println(g.reverseArts("sculptures"));
		g.export("sculptures.txt", 1, true);
	}
}
