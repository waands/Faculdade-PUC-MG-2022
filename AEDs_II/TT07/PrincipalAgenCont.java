class PrincipalAgenCont {
	public static void main (String[] args) throws Exception{
		Agenda agn = new Agenda();
		Contato carlos = new Contato("Carlos","31313131","carlos@rmail",131320);
		Contato carlita = new Contato("Carlita","31313131","carlos@rmail",131320);
		Contato roberto = new Contato("Roberto","22222222","roberto@rmail",354894);

		try {
			boolean resp = false;
			agn.inserir(carlos);
			agn.inserir(carlita);
			//MyIO.println(agn.raiz.esq.esq.primeiro.prox.contato.nome);
			agn.inserir(roberto);
			resp = agn.pesquisar("Carlita");
			MyIO.println("Carlita esta presente: " + resp);
			resp = agn.remover("Carlita");
			MyIO.println("Carlita foi removida: " + resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
