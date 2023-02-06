#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

int  scegliOpzione();
float  operazione(  int opz);
void main ();
float   op2, op1;
int  scegliOpzione(){
int   opzione;
 printf("Scegli opzione: \n");
printf("opzione 1: somma \n");
printf("opzione 2: sottrazione\n");
printf("opzione 3: prodotto\n");
printf("opzione 4: divisione\n");
printf("opzione 5: potenza\n");
scanf("%d",&opzione);
 if(    opzione>= 6  ||  opzione< 1    ) {
  opzione=  scegliOpzione();

}
 
return  opzione;

}




float  operazione(  int opz){
float   risultato;
 printf("inserisci primo numero:");
scanf("%f",&op1);
printf("inserisci secondo numero:");
scanf("%f",&op2);
 if(   opz== 1   ) {
  risultato=  op1+ op2 ;

}
 
 if(   opz== 2   ) {
  risultato=  op1- op2 ;

}
 
 if(   opz== 3   ) {
  risultato=  op1* op2 ;

}
 
 if(   opz== 4   ) {
  risultato=  op1/ op2 ;

}
 
 if(   opz== 5   ) {
 return pow( op1, op2) ;

}
 
return  risultato;

}



void main (){
int   flag =  1 ;
  while(  flag== 1  ){
int   opzione =   scegliOpzione();
float   risultato;
  risultato=  operazione( opzione);
printf("Risultato operazione: %f\n", risultato);
printf( "Vuoi fare un'altra operazione? (1:si 0:no): \n");
scanf("%d",&flag);

}
 
return ;

}


