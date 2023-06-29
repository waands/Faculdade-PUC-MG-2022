class Celula {
	public int elemento;
	public Celula prox;

	public Celula() {
		this(0);
	}
	public Celula (int x) {
		this.elemento = x;
		this.prox = null;
	}
}

class Lista {
	private Celula primeiro, ultimo;
	public Lista () {
		primeiro = new Celula();
		ultimo = primeiro;
	}
	public void inserirInicio(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			ultimo = tmp;
		}
		tmp = null;
	}
	public void inserirFim(int x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}
	public int removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

      Celula tmp = primeiro;
		primeiro = primeiro.prox;
		int resp = primeiro.elemento;
      tmp.prox = null;
      tmp = null;
		return resp;
	}
	public int removerFim() throws Exception{
		if (primeiro == ultimo)
			throw new Exception("Erro!");
		Celula i;
		for(i = primeiro; i.prox != ultimo; i = i.prox);
		int elemento = ultimo.elemento;
		ultimo = i;
		i = ultimo.prox = null;
		return elemento;
	}
	public void inserir(int x, int pos) throws Exception{
		int tamanho= tamanho();
		if (pos < 0 || pos > tamanho) {
			throw new Exception("Erro!");
		} else if (pos == 0) {
			inserirInicio(x);
		} else if (pos == tamanho) {
			inserirFim(x);
		} else {
			Celula i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox);
			Celula tmp = new Celula(x);
			tmp.prox = i.prox;
			i.prox = tmp;
			tmp = i = null;
		}
	}
	public int remover(int pos) throws Exception{
		int elemento, tamanho = tamanho();
		if (primeiro == ultimo || pos < 0 || pos >= tamanho){
			throw new Exception("Erro!");
		} else if (pos == 0) {
			elemento = removerInicio();
		} else if (pos == tamanho - 1) {
			elemento = removerFim();
		} else {
			Celula i = primeiro;
			for(int j = 0; j < pos; j++, i = i.prox);
			Celula tmp = i.prox;
			elemento = tmp.elemento;
			i.prox = tmp.prox;
			tmp.prox = null;
			i = tmp = null;
		}

		return elemento;
	}
	public void mostrar(){
		System.out.println("[");
		for(Celula i = primeiro.prox; i!= null; i = i.prox){
			System.out.print(i.elemento + " ");
		}
		System.out.println("]");
	}
	public int tamanho() {
      int tamanho = 0; 
      for(Celula i = primeiro.prox; i != null; i = i.prox, tamanho++);
      return tamanho;
   }
}

public class FlexiveisLista {
	public static void main (String[]args)
	{
		try {
			System.out.println("=== LISTA FLEXIVEL SIMPLESMENTE ENCADEADA ===");
			Lista lista = new Lista();

			lista.inserirInicio(1);
			lista.inserirInicio(0);
			lista.inserirFim(4);
			lista.inserirFim(5);
			lista.inserir(2, 2);
			lista.inserir(3, 3);
			lista.inserir(6, 6);
			lista.inserir(-1, 0);
			lista.inserirFim(7);
			lista.inserirFim(8);

			System.out.print("Apos insercoes: ");
			lista.mostrar();

			int x1 = lista.remover(3);
			int x2 = lista.remover(2);
			int x3 = lista.removerFim();
			int x4 = lista.removerInicio();
			int x5 = lista.remover(0);
			int x6 = lista.remover(4);
			lista.inserirFim(9);

			System.out.print("Apos remocoes (" +x1+ ", " +x2+ ", " +x3+ ", " +x4+ ", " +x5+ ", " +x6+ "): ");
			lista.mostrar();
		}
		catch(Exception erro) {
			System.out.println(erro.getMessage());
		}
	}
}