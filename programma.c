#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>




char*  passaStringa2(  char str[], char *str2){
  strcpy(str, "stringa2" ) ;
 strcpy(str2, "stringa3" ) ;
return  str;

}


void  passaStringa(  char *str){
char str2[1000] ;  strcpy(str, "ciao" ) ;
printf("stampo da passaStringa:%s\n", str);
 strcpy(str,  passaStringa2( str, str2)) ;
printf("stampo stringa 2: %s\n", str2);
return ;

}



void main (){
char stringa[1000] ={  "molto bene"  }; printf("esempio per controllare funzionamento parametri OUT funzione\n");
  passaStringa( stringa); 
printf("%s\n", stringa);
return ;

}


