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
int   a =  1 ;
float   b =  2.2 ;
int   x =  3 ;
char ans[1000] ={  "no"  };char ans1[1000] , taglia[1000] ;float   risultato =   sommac( a, x, b, taglia);
   stampa( "la somma  incrementata  è " ); 
printf("%s\n", taglia);
  stampa( " ed è pari a " ); 
printf("%f\n", risultato);
printf("vuoi continuare? (si/no) - inserisci due volte la risposta\n");
scanf("%s %s",ans,ans1);
 while( strcmp( ans,  "si" ) == 0 ){
 printf( "inserisci un intero:\n");
scanf("%d",&a);
printf( "inserisci un reale:\n");
scanf("%f",&b);
 risultato=  sommac( a, x, b, taglia);
  stampa( "la somma  incrementata  è " ); 
printf("%s\n", taglia);
  stampa( " ed è pari a " ); 
printf("%f\n", risultato);
printf( "vuoi continuare? (si/no):\n");
scanf("%s",ans);

}
 
printf("\n");
printf("ciao");
return ;

}


