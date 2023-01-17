#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

int   c =  1 ;
float  sommac(  int a,int d, float b, char*size){
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





void main (){
int   a =  1 ;
float   b =  2.2 ;
int   x =  3 ;
int   ans =  0 ;
char taglia[1000] ;int   ans1;
float   risultato =   sommac( a, x, b,& taglia);
 printf("la somma di %d e %f incrementata di %d e' %s\n", a, b, c, taglia);
printf("ed e' pari a %f\n", risultato);
printf("vuoi continuare? (1:si/0:no): - inserisci due volte la risposta\n");
scanf("%d,%d",&ans1,&ans);
 while(  ans== 1  ){
  risultato=  sommac( a, x, b,& taglia);
printf("la somma di %d e %f incrementata di %d e' %s\n", a, b, c, taglia);
printf("ed e' pari a %f\n", risultato);
printf("vuoi continuare? (1:si/0:no):");
scanf("%d",&ans);

}
 
printf("\n");
printf("ciao");
return ;

}


