#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

float  sommac(  int a,int d, float b, char *size);
void  stampa(  char messaggio[]);
void main ();
int   c =  1 ;
float  sommac(  int a,int d, float b, char *size){
float   result;
  result=    a+ b + c + d ;
 if(   result> 100   ) {
char valore[1000] ={  "grande"  };  strcpy(size, valore) ;

}
else {
char valore[1000] ={  "piccola"  };  strcpy(size, valore) ;

}
 
return  result;

}


void  stampa(  char messaggio[]){
int   a;
int   i;
 for( int  x =  4 ;  x >  1 ;  x--){
 printf("\n");

}
 
printf("%s\n", messaggio);
return ;

}





void main (){
int   ans =  0 ;
int   c =  2 ;
int   x, a;
float   b;
char taglia[1000] ;float   risultato;
  a= 1 ;
b= 2.2 ;
x= 3 ;
printf("%d\n", c);
 risultato=  sommac( a, x, b, taglia);
  stampa( "la somma  incrementata  è " ); 
printf("%s\n", taglia);
  stampa( " ed è pari a " ); 
printf("%f\n", risultato);
printf("vuoi continuare? (1/si 0/no)\n");
scanf("%d",&ans);
 while(  ans== 1  ){
 printf("inserisci un intero:\n");
scanf("%d",&a);
printf("inserisci un reale:\n");
scanf("%f",&b);
 risultato=  sommac( a, x, b, taglia);
  stampa( "la somma  incrementata  è " ); 
printf("%s\n", taglia);
  stampa( " ed è pari a " ); 
printf("%f\n", risultato);
printf("vuoi continuare? (1/si 0/no):\n");
scanf("%d",&ans);

}
 
printf("\n");
printf("ciao");
return ;

}


