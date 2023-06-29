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

class Pilha {
	private Celula topo;

	public Pilha() {
		topo = null;
	}
	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}
	public int remover() throws Exception {
		if (topo == null)
			throw new Exception("Erro! topo vazio");
		int elemento = topo.elemento;//salvando o elemento p/ retornar
		Celula tmp = topo;
		topo = topo.prox;//mudando o topo para o elemnto de baixo
		tmp.prox = null;//destruindo o topo
		tmp = null;//destruindo o topo
		return(elemento);
	}
	public void mostrar(){
		System.out.println("[");
		for(Celula i = topo; i!= null; i = i.prox){
			System.out.print(i.elemento + " ");
		}
		System.out.println("]");
	}
}

public class FlexiveisPilha {
	public static void main (String[]args)
	{
		try {
			System.out.println(" ==== PILHA FLEXIVEL ====");
			Pilha pilha = new Pilha();
         	int x1, x2, x3;

			pilha.inserir(0);
			pilha.inserir(1);
			pilha.inserir(2);
			pilha.inserir(3);
			pilha.inserir(4);
			pilha.inserir(5);

			System.out.print("Apos insercoes: ");
			pilha.mostrar();

			x1 = pilha.remover();
			x2 = pilha.remover();
			x3 = pilha.remover();

			System.out.print("Apos as remocoes (" + x1 + "," + x2 + "," + x3 + "): ");
			pilha.mostrar();

		}
		catch(Exception erro) {
			System.out.println(erro.getMessage());
		}
	}
}