
/**
 * TP05 - Questao 3
 * @author Gabriel Oliveira Costa
 * Matrícula: 780119
 */
import java.util.*;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Locale;
import java.text.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GameAVL { // CLASSE GAME
	private int app_id, age, dlcs, avg_pt;
	private float price, upvotes;
	private String name, owners, website, developers;
	private String data;
	private String[] languages, genres;
	private java.util.Date release_date;
	private Boolean windows, mac, linux;

	public GameAVL(int app_id, int age, int dlcs, int avg_pt, float price, float upvotes, String name, String owners,
			String website, String developers, String[] languages, String[] genres, java.util.Date release_date) { // construtor
		// completo
		this.app_id = app_id;
		this.age = age;
		this.dlcs = dlcs;
		this.avg_pt = avg_pt;
		this.price = price;
		this.upvotes = upvotes;
		this.name = name;
		this.owners = owners;
		this.website = website;
		this.developers = developers;
		this.languages = languages;
		this.genres = genres;
		this.release_date = release_date;
	}

	public GameAVL() { // construtor vazio

	}

	public GameAVL(String id) { // construtor com apenas app_id(usado na leitura)
		this.app_id = Integer.parseInt(id);
	}

	public GameAVL(int id) { // construtor com apenas app_id(usado na leitura)
		this.app_id = id;
	}

	public int getApp_id() {
		return this.app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDlcs() {
		return this.dlcs;
	}

	public void setDlcs(int dlcs) {
		this.dlcs = dlcs;
	}

	public int getAvg_pt() {
		return this.avg_pt;
	}

	public void setAvg_pt(int avg_pt) {
		this.avg_pt = avg_pt;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getUpvotes() {
		return this.upvotes;
	}

	public void setUpvotes(float upvotes) {
		this.upvotes = upvotes;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwners() {
		return this.owners;
	}

	public void setOwners(String owners) {
		this.owners = owners;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDevelopers() {
		return this.developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}

	public String[] getLanguages() {
		return this.languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	public String[] getGenres() {
		return this.genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public java.util.Date getRelease_date() {
		return this.release_date;
	}

	public void setRelease_date(java.util.Date release_date) {
		this.release_date = release_date;
	}

	public Boolean getWindows() {
		return windows;
	}

	public void setWindows(Boolean windows) {
		this.windows = windows;
	}

	public Boolean getMac() {
		return this.mac;
	}

	public void setMac(Boolean mac) {
		this.mac = mac;
	}

	public Boolean getLinux() {
		return this.linux;
	}

	public void setLinux(Boolean linux) {
		this.linux = linux;
	}

	public String fazerData(String inputData) { // METODO PARA FAZER DATA
		if (inputData.length() > 10) {
			String[] tmpData = inputData.split(",");
			String mes = "" + tmpData[0].charAt(0) + tmpData[0].charAt(1) + tmpData[0].charAt(2);
			String outputData = mes + "/" + tmpData[tmpData.length - 1].substring(1);
			return outputData;
		} else {
			String[] tmpData = inputData.split(" ");
			String outputData = tmpData[0] + "/" + tmpData[tmpData.length - 1];
			return outputData;
		}
	}

	public String calcularHoras() { // metodo para calcular horas e minutos e criar string com formato ideal para a
									// saida
		if (this.avg_pt == 0) {
			return null;
		} else {
			int horas = Math.round((float) (this.avg_pt / 60));
			int minutos = Math.round(this.avg_pt % 60);
			String time = (Integer.toString(horas) + "h " + Integer.toString(minutos) + "m");
			return time;
		}
	}

	public void calcularPorcentagem(float u, float d) { // calcular porcentagem dos upvotes
		this.upvotes = (u / (u + d) * 100);
	}

	public String fazerStringPorcentagem() { // metodo para fazer string ideal para a saída da porcentagem dos upvotes
		int tmp = Math.round(this.upvotes);
		String porcentagem = (Integer.toString(tmp) + "%");
		return porcentagem;
	}

	public void checkIfWebsiteNull() {
		if (this.website == "") {
			this.website = null;
		}
	}

	public void ler(String input) throws IOException { // METODO PARA LER LINHA DE GAMES.CSV E GUARDAR ATRIBUTOS EM SUAS
														// VARIÁVEIS
		DateFormat df = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
		DateFormat df2 = new SimpleDateFormat("MMM yyyy", Locale.US);
		int controle = 0;
		int numVirgulas = 0;
		String tmp = input;
		for (int i = 2; i < input.length(); i++) {
			if (input.charAt(i - 2) == ',') // contar virgulas
			{
				numVirgulas++;
			}
			if (controle == 0 && input.charAt(i - 1) == '\"' && input.charAt(i + 1) != '[') // ler nome com " "
			{
				controle++;
				String tmpName = "";
				for (int cn = 1; tmp.charAt(cn) != '\"'; cn++) {
					if (tmp.charAt(cn) == ',') {
						numVirgulas--;
					}
					tmpName += tmp.charAt(cn);
				}
				this.name = tmpName;
			} else if (controle == 0 && numVirgulas == 1) // ler nome
			{
				controle++;
				this.name = tmp.split(",")[0];
			}
			if (controle == 1 && numVirgulas == 2) // ler data
			{
				controle++;
				String tmpData = "";
				if (tmp.charAt(0) == '\"') {
					tmp = tmp.substring(1);
					tmpData = tmp.split("\"")[0];
				} else {
					tmpData = tmp.split(",")[0];
				}
				if (tmpData.length() > 10) {
					numVirgulas--; // tirar virgula dentro da data da contagem
					try {
						this.release_date = df.parse(tmpData);
						this.data = this.fazerData(tmpData);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					try {
						this.release_date = df2.parse(tmpData);
						this.data = this.fazerData(tmpData);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			if (controle == 2 && numVirgulas == 3) // ler owners
			{
				controle++;
				this.owners = tmp.split(",")[0];
			}
			if (controle == 3 && numVirgulas == 4) { // ler age
				controle++;
				this.age = Integer.parseInt(tmp.split(",")[0]);
			}
			if (controle == 4 && numVirgulas == 5) { // ler preco
				controle++;
				this.price = Float.parseFloat(tmp.split(",")[0]);
			}
			if (controle == 5 && numVirgulas == 6) { // ler dlcs
				controle++;
				this.dlcs = Integer.parseInt(tmp.split(",")[0]);
			}
			if (controle == 6 && numVirgulas == 7 && tmp.charAt(0) == '[') { // ler languages
				controle++;
				String tmpLang;
				tmpLang = tmp.split("']")[0];
				tmpLang += "'";
				tmpLang = tmpLang.replaceAll("\'", "");
				this.languages = tmpLang.split(", ");
				this.languages[0] = this.languages[0].substring(1);
				String strLang = Arrays.toString(this.languages);
				for (int cl = 0; cl < strLang.length(); cl++) // contar virgulas das linguagens
				{
					if (strLang.charAt(cl) == ',') {
						numVirgulas--;
					}
				}
			}
			if (controle == 7 && numVirgulas == 8) { // ler website
				controle++;
				if (tmp.charAt(0) == '\"') {
					tmp = tmp.substring(1);
					this.website = tmp.split("\"")[0];
					for (int cw = 0; cw < this.website.length(); cw++) // contar virgulas do website
					{
						if (this.website.charAt(cw) == ',') {
							numVirgulas--;
						}
					}
				}
				this.website = tmp.split(",")[0];
			}
			if (controle == 8 && numVirgulas == 9) { // ler windows
				controle++;
				this.windows = Boolean.parseBoolean(tmp.split(",")[0]);
			}
			if (controle == 9 && numVirgulas == 10) { // ler mac
				controle++;
				this.mac = Boolean.parseBoolean(tmp.split(",")[0]);
			}
			if (controle == 10 && numVirgulas == 11) { // ler linux
				controle++;
				this.linux = Boolean.parseBoolean(tmp.split(",")[0]);
			}
			if (controle == 11 && numVirgulas == 12) { // ler upvotes
				controle++;
				String[] tmpUpvotes = tmp.split(",", 3);
				float up = Float.parseFloat(tmpUpvotes[0]);
				float down = Float.parseFloat(tmpUpvotes[1]);
				this.calcularPorcentagem(up, down); // calcular porcentagem de upvotes
			}
			if (controle == 12 && numVirgulas == 14) { // ler avg_pt(average play time = minutos)
				controle++;
				this.avg_pt = Integer.parseInt(tmp.split(",")[0]);
			}
			if (controle == 13 && numVirgulas == 15) { // ler developers
				controle++;
				if (tmp.charAt(0) == '\"') {
					tmp = tmp.substring(1);
					this.developers = tmp.split("\"")[0];
					for (int cd = 0; cd < this.developers.length(); cd++) // contar virgulas dos developers
					{
						if (this.developers.charAt(cd) == ',') {
							numVirgulas--;
						}
					}
				} else {
					this.developers = tmp.split(",")[0];
				}
			}
			if (controle == 14 && numVirgulas == 16) { // ler genres
				controle++;
				tmp = tmp.replaceAll("\"", "");
				this.genres = tmp.split(",");
			}
			tmp = input.substring(i); // tirar parte da string que já "passou" pelo for
		}
		this.checkIfWebsiteNull();
	}

	public void imprimir() { // metodo para imprimir saida
		MyIO.println(this.getApp_id() + " " + this.getName() + " " + this.data + " " + this.getOwners() + " "
				+ this.getAge() + " " + this.getPrice() + " " + this.getDlcs() + " "
				+ Arrays.toString(this.getLanguages()) + " " + this.getWebsite() + " " + this.getWindows() + " "
				+ this.getMac() + " " + this.getLinux() + " " + this.fazerStringPorcentagem() + " "
				+ this.calcularHoras() + " " + this.getDevelopers() + " " + Arrays.toString(this.getGenres()));
	}

	public static boolean pesquisaBinaria(String nome, String[] gameNames) { // PESQUISA BINARIA
		int inicio = 0;
		int meio = 0;
		int fim = gameNames.length - 1;
		while (inicio <= fim) {
			meio = (fim + inicio) / 2;
			if (gameNames[meio].equals(nome)) {
				return true;
			} else if (nome.compareTo(gameNames[meio]) > 0) {
				inicio = meio + 1;
			} else {
				fim = meio - 1;
			}
		}
		return false;

	}
}

/**
 * NoAVL da arvore binaria
 * 
 * @author Max do Val Machado
 */

class NoAVL {
	public GameAVL elemento; // Conteudo do no.
	public NoAVL esq, dir; // Filhos da esq e dir.
	public int nivel; // Numero de niveis abaixo do no

	/**
	 * Construtor da classe
	 * @param elemento Conteudo do no.
	 */
	public NoAVL(GameAVL elemento) {
		this(elemento, null, null, 1);
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq      NoAVL da esquerda.
	 * @param dir      NoAVL da direita.
	 */
	public NoAVL(GameAVL elemento, NoAVL esq, NoAVL dir, int nivel) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
		this.nivel = nivel;
	}

	/**
	 * Cálculo do número de níveis a partir de um vértice
	 */
	public void setNivel() {
		this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
	}

	/**
	 * Retorna o número de níveis a partir de um vértice
	 * @param no nó que se deseja o nível.
	 */
	public static int getNivel(NoAVL no) {
		return (no == null) ? 0 : no.nivel;
	}
}

/**
 * Arvore binaria de pesquisa
 * 
 * @author Max do Val Machado
 */
class ArvoreAVL {
	public static int comp = 0;
	private NoAVL raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public ArvoreAVL() {
		raiz = null;
	}

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * 
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir, <code>false</code> em caso
	 *         contrario.
	 */
	public boolean pesquisar(String x) {
		MyIO.print("raiz ");
		return pesquisar(x, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * 
	 * @param x Elemento que sera procurado.
	 * @param i NoAVL em analise.
	 * @return <code>true</code> se o elemento existir, <code>false</code> em caso
	 *         contrario.
	 */
	private boolean pesquisar(String x, NoAVL i) {
		boolean resp;
		if (i == null) {
			comp++;
			resp = false;

		} else if (x.equals(i.elemento.getName())) {
			comp = comp + 2;
			resp = true;

		} else if (x.compareTo(i.elemento.getName()) < 0) {
			comp = comp + 3;
			MyIO.print("esq ");
			resp = pesquisar(x, i.esq);

		} else {
			comp = comp + 3;
			MyIO.print("dir ");
			resp = pesquisar(x, i.dir);
		}
		return resp;
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharCentral() {
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * 
	 * @param i NoAVL em analise.
	 */
	private void caminharCentral(NoAVL i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * 
	 * @param i NoAVL em analise.
	 */
	private void caminharPre(NoAVL i) {
		if (i != null) {
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * 
	 * @param i NoAVL em analise.
	 */
	private void caminharPos(NoAVL i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + " "); // Conteudo do no.
		}
	}

	/**
	 * Metodo publico iterativo para inserir elemento.
	 * 
	 * @param x Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(GameAVL x) throws Exception {
		raiz = inserir(x, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * 
	 * @param x Elemento a ser inserido.
	 * @param i NoAVL em analise.
	 * @return NoAVL em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	private NoAVL inserir(GameAVL x, NoAVL i) throws Exception {
		if (i == null) {
			comp++;
			i = new NoAVL(x);

		} else if (x.getName().compareTo(i.elemento.getName()) < 0) {
			comp = comp + 2;
			i.esq = inserir(x, i.esq);

		} else if (x.getName().compareTo(i.elemento.getName()) > 0) {
			comp = comp + 3;
			i.dir = inserir(x, i.dir);

		} else {
			comp = comp + 3;
			throw new Exception("Erro ao inserir!");
		}

		return balancear(i);
	}

	/**
	 * Metodo publico iterativo para remover elemento.
	 * 
	 * @param x Elemento a ser removido.
	 * @throws Exception Se nao encontrar elemento.
	 */
	public void remover(String x) throws Exception {
		raiz = remover(x, raiz);
	}
	
	

	/**
	 * Metodo privado recursivo para remover elemento.
	 * 
	 * @param x Elemento a ser removido.
	 * @param i NoAVL em analise.
	 * @return NoAVL em analise, alterado ou nao.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private NoAVL remover(String x, NoAVL i) throws Exception {

		if (i == null) {
			comp++;
			throw new Exception("Erro ao remover!");

		} else if (x.compareTo(i.elemento.getName()) < 0) {
			comp = comp + 2;
			i.esq = remover(x, i.esq);

		} else if (x.compareTo(i.elemento.getName()) > 0) {
			comp = comp + 3;
			i.dir = remover(x, i.dir);

			// Sem no a direita.
		} else if (i.dir == null) {
			comp = comp + 4;
			i = i.esq;

			// Sem no a esquerda.
		} else if (i.esq == null) {
			comp = comp + 5;
			i = i.dir;

			// NoAVL a esquerda e no a direita.
		} else {
			comp = comp + 5;
			i.esq = maiorEsq(i, i.esq);
		}

		return balancear(i);
	}

	/**
	 * Metodo para trocar o elemento "removido" pelo maior da esquerda.
	 * 
	 * @param i NoAVL que teve o elemento removido.
	 * @param j NoAVL da subarvore esquerda.
	 * @return NoAVL em analise, alterado ou nao.
	 */
	private NoAVL maiorEsq(NoAVL i, NoAVL j) {
		comp++;
		// Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

			// Existe no a direita.
		} else {
			// Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}
	private NoAVL balancear(NoAVL no) throws Exception {
		if (no != null) {
			int fator = NoAVL.getNivel(no.dir) - NoAVL.getNivel(no.esq);
			// Se balanceada
			if (Math.abs(fator) <= 1) {
				no.setNivel();
			// Se desbalanceada para a direita
			} else if (fator == 2) {
				int fatorFilhoDir = NoAVL.getNivel(no.dir.dir) - NoAVL.getNivel(no.dir.esq);
				// Se o filho a direita tambem estiver desbalanceado
				if (fatorFilhoDir == -1) {
					no.dir = rotacionarDir(no.dir);
				}
				no = rotacionarEsq(no);
			// Se desbalanceada para a esquerda
			} else if (fator == -2) {
				int fatorFilhoEsq = NoAVL.getNivel(no.esq.dir) - NoAVL.getNivel(no.esq.esq);
				// Se o filho a esquerda tambem estiver desbalanceado
				if (fatorFilhoEsq == 1) {
					no.esq = rotacionarEsq(no.esq);
				}
				no = rotacionarDir(no);
			} else {
				throw new Exception(
						"Erro no NoAVL(" + no.elemento + ") com fator de balanceamento (" + fator + ") invalido!");
			}
		}
		return no;
	}

	private NoAVL rotacionarDir(NoAVL no) {
		//System.out.println("Rotacionar DIR(" + no.elemento + ")");
		NoAVL noEsq = no.esq;
		NoAVL noEsqDir = noEsq.dir;

		noEsq.dir = no;
		no.esq = noEsqDir;
		no.setNivel(); // Atualizar o nivel do no
		noEsq.setNivel(); // Atualizar o nivel do noEsq

		return noEsq;
	}

	private NoAVL rotacionarEsq(NoAVL no) {
		//System.out.println("Rotacionar ESQ(" + no.elemento + ")");
		NoAVL noDir = no.dir;
		NoAVL noDirEsq = noDir.esq;

		noDir.esq = no;
		no.dir = noDirEsq;

		no.setNivel(); // Atualizar o nivel do no
		noDir.setNivel(); // Atualizar o nivel do noDir
		return noDir;
	}
}

/**
 * Classe para tratar dos objetos games (ler games.csv, guardar em lista, pegar
 * um jogo a partir de seu ID)
 * 
 * @author Gabriel Oliveira Costa
 * @version 1 11/2022
 */
class LerObjetoAVL {
	static List<GameAVL> games = new ArrayList<GameAVL>(); // lista de objetos

	public static void fazerListaGamesCSV() { // metodo para ler games.csv e fazer lista de objetos
		BufferedReader objReader = null;
		try {
			String strCurrentLine;
			objReader = new BufferedReader(new FileReader("/tmp/games.csv"));
			while ((strCurrentLine = objReader.readLine()) != null) {
				String[] id = strCurrentLine.split(",", 0);
				GameAVL jogo = new GameAVL(id[0]);
				jogo.ler(strCurrentLine);
				games.add(jogo); // adiciona jogos a lista de objetos
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static GameAVL getFromID(int id) { // metodo para pegar objeto jogo da lista a partir de seu id
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getApp_id() == id) {
				return games.get(i);
			}
		}
		return (null);
	}
}

public class MainArvoreAVL {
	public static boolean isFim(String palavra) { // Metodo para verificar se "FIM" foi entrado
		return (palavra.length() == 3 && palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I'
				&& palavra.charAt(2) == 'M');
	}

	public static void main(String[] args) throws IOException { // METODO MAIN
		long startTime = System.nanoTime();
		LerObjetoAVL.fazerListaGamesCSV();
		ArvoreAVL arvoreBinaria = new ArvoreAVL();
		String[] entrada1 = new String[1000];
		String[] entrada2 = new String[1000];
		int cont1 = -1;
		int cont2 = -1;
		int quantComandos = 0;

		// // ler primeira entrada de IDs
		do {
			cont1++;
			entrada1[cont1] = MyIO.readLine();
		} while (!isFim(entrada1[cont1]));
		// ler quantidade de comandos a serem lidos
		quantComandos = Integer.parseInt(MyIO.readLine());
		// ler segunda entrada com comandos
		do {
			cont2++;
			entrada2[cont2] = MyIO.readLine();
			quantComandos--;
		} while (quantComandos > 0);
		for (int i = 0; i < cont1; i++) {
			try {
				arvoreBinaria.inserir(LerObjetoAVL.getFromID(Integer.parseInt(entrada1[i])));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < cont2 + 1; i++) {
			if (entrada2[i].split(" ")[0].equals("I")) {
				try {
					arvoreBinaria.inserir(LerObjetoAVL.getFromID(Integer.parseInt(entrada2[i].split(" ")[1])));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (entrada2[i].split(" ")[0].equals("R")) {
				String palavraRem = "";
				for (int w = 1; w < entrada2[i].split(" ").length - 1; w++){ //palavra que vai ser removida
					palavraRem += entrada2[i].split(" ")[w] + " ";
				}
				palavraRem += entrada2[i].split(" ")[entrada2[i].split(" ").length - 1]; // ultima palavra sem " "
				try {
					arvoreBinaria.remover(palavraRem);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String nomePesq = MyIO.readLine();
		while (!isFim(nomePesq)) {
			MyIO.println(nomePesq);
			if (arvoreBinaria.pesquisar(nomePesq)) {
				MyIO.println("SIM");
			} else {
				MyIO.println("NAO");
			}
			nomePesq = MyIO.readLine();
		}
		long endTime = System.nanoTime();
		long tempoTotal = endTime - startTime;
		try {
			FileWriter fw = new FileWriter("780119_avl.txt");
			fw.write("780119\t" + tempoTotal / 1000000 + "ms\t" + ArvoreAVL.comp);
			fw.close();
		} catch (IOException e) {
			MyIO.println("");
		}
	}
}
