import java.util.*;

class MaxFotografo {
	public static void main (String[] args){
		int t = MyIO.readInt();


		for (int j = 0; j < t; j++){
			String tmp1 = MyIO.readLine();
			String[] tmp2 = tmp1.split(" ");

			int n = Integer.parseInt(tmp2[0]);
			int x = Integer.parseInt(tmp2[1]);

			int pontuacaoParcial = 0;
			int pontuacaoFinal = 0;
			int control = 0;

			tmp1 = MyIO.readLine();
			tmp2 = tmp1.split(" ");

			bubbleS(tmp2);

			for (int i = 0; i < tmp2.length; i++){
				for (int k = 0 + i; k < tmp2.length && control == 0; k++)
				{
					if ((Integer.parseInt(tmp2[i]) + x) <= Integer.parseInt(tmp2[k])){
						pontuacaoParcial++;
						tmp2[k] = "0";
						control++;
					}
				}

				if (pontuacaoParcial > 0) {
					pontuacaoFinal++;
				}

				control = 0;
				pontuacaoParcial = 0;
			}

			if (pontuacaoFinal >= n){
				System.out.println("SIM");
			} else {
				System.out.println("NAO");
			}
		}
	}

	static void bubbleS(String[] arr) {  
        int n = arr.length;  
        int temp = 0;  

        for(int i=0; i < n; i++){  
            for(int j=1; j < (n-i); j++){  
                    if(Integer.parseInt(arr[j-1]) > Integer.parseInt(arr[j])){ 
                    	//[j-1] == i
                        //swap elements  
                        temp = Integer.parseInt(arr[j-1]);  
                        arr[j-1] = arr[j];  
                        arr[j] = String.valueOf(temp);  
                }  
            }                          
        }  
    }  

}