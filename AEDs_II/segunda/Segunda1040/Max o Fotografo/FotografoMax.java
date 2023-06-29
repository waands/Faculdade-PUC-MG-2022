import java.util.*;

public class FotografoMax {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numTestes = sc.nextInt();
		String[] saida = new String[numTestes];
		for (int t = 0; t < numTestes; t++) {
			int n = sc.nextInt();
			int x = sc.nextInt();
			int compativeis = 0;
			int[] alturas = new int[2 * n];
			for (int i = 0; i < 2 * n; i++) {
				alturas[i] = sc.nextInt();
			}
			Arrays.sort(alturas);
			int[] fileiraBaixos = new int[n];
			int[] fileiraAltos = new int[n];
			for (int i = 0; i < n; i++) {
				fileiraBaixos[i] = alturas[i];
				fileiraAltos[i] = alturas[i + n];
			}
			for (int i = 0; i < n; i++) {
				if (fileiraAltos[i] - fileiraBaixos[i] >= x) {
					compativeis++;
				}
			}
			if (compativeis >= n) {
				saida[t] = "SIM";
			} else {
				saida[t] = "NAO";
			}
		}
		for (int i = 0; i < saida.length; i++) {
			System.out.println(saida[i]);
		}
		sc.close();
	}
}