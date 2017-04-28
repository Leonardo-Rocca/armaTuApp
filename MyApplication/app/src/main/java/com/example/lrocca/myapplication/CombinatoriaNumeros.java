package com.example.lrocca.myapplication;

import java.util.ArrayList;

public class CombinatoriaNumeros {
	
static 	ArrayList<Integer> listaIndices= new ArrayList<Integer>();
static int N=10;
static int K=5;
private static int contador;

	public static ArrayList<Integer> devolverCombinatoria(String[] args) {
		combinatoriaNum();
		return listaIndices;
}

	
public static ArrayList<String> devolverCombinatoriaString() {
	ArrayList<String> listaIndices= new ArrayList<String>();
	int total = 0;
	int a = 0;
	int b,c,d,e;
	for (;a<6;a++){
		b=a+1;
		for (;b<10;b++){
			c=b+1;
			for (;c<10;c++){
				d=c+1;
				for (;d<10;d++){
					e=d+1;
					for (;e<10;e++){
						String cadena = String.valueOf(a)+String.valueOf(b)+String.valueOf(c)+String.valueOf(d)+String.valueOf(e);
						listaIndices.add(cadena);
			//			System.out.printf("%s\n",cadena);
						total++;
						if(total == 126){
		//					System.out.printf("%s\n","---------------");
							
						}
					} 
				} 
			} 
		}
	}
//	System.out.printf("%d\n",total);
	return listaIndices;
}

	private static void combinatoriaNum() {

		int i;
		  for (i=1; i<N;i++){
		    frecursiva(i,i+1,1);
	}
		  System.out.printf(" cantidad %d\n",contador);
	}
	
	

private static void frecursiva(int numero, int indice, int longitud){
		  int i;
		  if (longitud==K) {
		   contador ++;
		   			if(numero != 412345){
		   				listaIndices.add(numero);}
		   			if(numero == 412345){
			   listaIndices.add(12345);
		   }
			  System.out.printf("%d\n",numero);
		  }
		  else {
		    for (i=indice; i<=N; i++){
		      frecursiva(numero*10+i,i+1,longitud+1);
		    }
		  }	
	}

 //--------------v	2	
/*
public static

for(int i=1 to m
>    v(i)=i
>next
>
>Call ImprimirCombinacion
>
>do while v(1)<>n-m
>    i=m
>    do while v(i)<>n+i-m
>        i=i-1
>    enddo
>    v(i)=v(i)+1
>    for i=i+1 to m
>        v(i)=v(i-1)+1
>    next
>    call ImprimirCombinacion
>enddo
>*/

//------------v3
/*private static void combinatoriaNum3() {
int n, m, i, nuevo_valor;

n = 10;
m = 5;


for(i = 0; i < m; ++i)
	listaIndices.add(i+1);// comb[i] = i + 1;

while (true) {

   for (i = 0; i < m; ++i)
		  System.out.printf("%d\n",listaIndices.get(i));//    printf("%d", comb[i]);
   
   for (i = m - 1; i > -1 && listaIndices.get(i) ==  n - ((m - 1) - i); --i)
      ;
   if (i == -1)
      return ;
   nuevo_valor = listaIndices.get(i);
   listaIndices.add(i+1);
   while (i < m)
	   listaIndices.add( ++nuevo_valor);
}
}*/

}
