package connexion;

import Ressource.Ressource;

public class Automate {

 /*attribut de l'etat courant*/
 private int etatCourant = Ressource.ETAT_CLOSED;
 
 public Automate () {
  
 }
 
 public void setEtatCourant (int etat) {
  this.etatCourant = etat;
 }
 
 public int getEtatCourant () {
  return this.etatCourant;
 }
 
 /*change l'etat de l'automate, prend en compte la continuité des etats (on peut pas passer de closed a established)*/
 public void changementEtat (int etat) {
  if (this.etatCourant == Ressource.ETAT_CLOSED) {
   if (etat != Ressource.ETAT_LISTEN || etat != Ressource.ETAT_SYN_SENT) {
    System.out.println("Etat impossible a atteindre depuis CLOSED");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_LISTEN) {
   if (etat != Ressource.ETAT_CLOSED || etat != Ressource.ETAT_SYN_SENT || etat != Ressource.ETAT_SYN_RCVD) {
    System.out.println("Etat impossible a atteindre depuis LISTEN");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_SYN_SENT) {
   if (etat != Ressource.ETAT_CLOSED || etat != Ressource.ETAT_SYN_RCVD || etat != Ressource.ETAT_ESTABLISHED) {
    System.out.println("Etat impossible a atteindre depuis SYN_SENT");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_SYN_RCVD) {
   if (etat != Ressource.ETAT_ESTABLISHED || etat != Ressource.ETAT_FIN_WAIT_1) {
    System.out.println("Etat impossible a atteindre depuis SYN_RCVD");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_ESTABLISHED) {
   if (etat != Ressource.ETAT_FIN_WAIT_1 || etat != Ressource.ETAT_FIN_WAIT_2) {
    System.out.println("Etat impossible a atteindre depuis ESTABLISHED");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_FIN_WAIT_1) {
   if (etat != Ressource.ETAT_FIN_WAIT_2 || etat != Ressource.ETAT_CLOSING) {
    System.out.println("Etat impossible a atteindre depuis FIN WAIT 1");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_FIN_WAIT_2) {
   if (etat != Ressource.ETAT_TIME_WAIT) {
    System.out.println("Etat impossible a atteindre depuis FIN WAIT 2");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_CLOSING) {
   if (etat != Ressource.ETAT_TIME_WAIT) {
    System.out.println("Etat impossible a atteindre depuis CLOSING");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_TIME_WAIT) {
   if (etat != Ressource.ETAT_CLOSED) {
    System.out.println("Etat impossible a atteindre depuis TIME WAIT");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_CLOSE_WAIT) {
   if (etat != Ressource.ETAT_LAST_ACK) {
    System.out.println("Etat impossible a atteindre depuis CLOSE WAIT");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else if (this.etatCourant == Ressource.ETAT_LAST_ACK) {
   if (etat != Ressource.ETAT_CLOSED) {
    System.out.println("Etat impossible a atteindre depuis LAST ACK");
   }
   else
   {
    setEtatCourant(etat);
   }
  }
  else
  {
   System.out.println("Etat inconnu");
  }
 }
 
 public void afficheEtatCourant () {
  switch (this.etatCourant)
  {
  case Ressource.ETAT_CLOSE_WAIT :
   System.out.println("Etat actuel : ETAT_CLOSE_WAIT");
   break;
  case Ressource.ETAT_CLOSED :
   System.out.println("Etat actuel : ETAT_CLOSED");
   break;
  case Ressource.ETAT_CLOSING :
   System.out.println("Etat actuel : ETAT_CLOSING");
   break;  
  case Ressource.ETAT_ESTABLISHED :
   System.out.println("Etat actuel : ETAT_ESTABLISHED");
   break;
  case Ressource.ETAT_FIN_WAIT_1 :
   System.out.println("Etat actuel : ETAT_FIN_WAIT_1");
   break;  
  case Ressource.ETAT_FIN_WAIT_2 :
   System.out.println("Etat actuel : ETAT_FIN_WAIT_2");
   break;  
  case Ressource.ETAT_LAST_ACK :
   System.out.println("Etat actuel : ETAT_LAST_ACK");
   break;  
  case Ressource.ETAT_LISTEN :
   System.out.println("Etat actuel : ETAT_LISTEN");
   break;  
  case Ressource.ETAT_SYN_RCVD :
   System.out.println("Etat actuel : ETAT_SYN_RCVD");
   break;  
  case Ressource.ETAT_SYN_SENT :
   System.out.println("Etat actuel : ETAT_SYN_SENT");
   break;  
  case Ressource.ETAT_TIME_WAIT :
   System.out.println("Etat actuel : ETAT_TIME_WAIT");
   break;  
  default :
   System.out.println("Etat inconnu");
   break;
  }
 }
 
}
