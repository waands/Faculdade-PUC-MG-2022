import java.util.*;

class Evergreen {
	public static void main (String[] args){

		int n = MyIO.readInt();
		String nome; 
		String sobrenome;
		int generalSize;

		for (int i = 0; i < n; i++) {
			String nomeFinal = "";

			nome = MyIO.readLine();
			sobrenome = MyIO.readLine();
			
			if (nome.length() % 2 != 0){
				nome += " ";
			}
			if (sobrenome.length() % 2 != 0) {
				sobrenome += " ";
			}

			generalSize = (nome.length()+sobrenome.length())/2;

			char o = ' ';

			for (int j = 0; j < generalSize; j += 2){
				if (j < nome.length())
				{
					o = nome.charAt(j);
					nomeFinal += o;
					o = nome.charAt(j+1);
					nomeFinal += o;
				}
				if (j < sobrenome.length())
				{
					o = sobrenome.charAt(j);
					nomeFinal += o;
					o = sobrenome.charAt(j+1);
					nomeFinal += o;
				}
			}

			System.out.println(nomeFinal);
		}
	}
}