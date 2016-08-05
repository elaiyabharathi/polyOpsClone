import java.util.*;
class PolyTerm
{

        double coeff;

        int pow;

        public PolyTerm(double coeff, int pow)

        {

                this.coeff = coeff;

                this.pow = pow;

        }

}

class Polynomial
{       

        ArrayList<PolyTerm> polynomial;

        public Polynomial() {

                super();

                this.polynomial = new ArrayList<PolyTerm>();

        }

        

        public void add(double coeff, int pow)

        {

                this.polynomial.add(new PolyTerm(coeff,pow));

        }
        
        public void makePolynomial(String Poly){
              int len = Poly.length();
              String terms[] = Poly.split("(\\+)|(\\-)");
              for(String term: terms){
                     //System.out.println(term);
                     int i=-1;
                     int coeff=0;
                     int power=0;
                     i = term.indexOf("x^"); 
                     //System.out.println(i);
                     if(i!=-1){
                           //System.out.println(term.substring(0,i));
                           coeff = Integer.parseInt(term.substring(0,i));
                           int indexOfTermInTerms = Poly.indexOf(term); 
                           if(indexOfTermInTerms != 0){
                                  if(Poly.charAt(indexOfTermInTerms-1)=='-'){
                                         coeff = -coeff;
                                  }
                           }
                           power = Integer.parseInt(term.substring(i+2,term.length()));
                     }
                     else if(!term.equals("")){
                           coeff = Integer.parseInt(term);
                           int indexOfTermInTerms = Poly.indexOf(term); 
                           if(indexOfTermInTerms != 0){
                                  if(Poly.charAt(indexOfTermInTerms-1)=='-'){
                                         coeff = -coeff;
                                  }
                           }
                           power = 0;
                     }
                     add(coeff,power);
                     //System.out.println("coeff"+coeff+" power"+power);
              }
        }
        
        public Polynomial multiply(Polynomial poly1,Polynomial poly2)
        {
           HashMap<Integer,Double> multi = new HashMap<Integer,Double>();
           Polynomial result = new Polynomial();

           ArrayList<PolyTerm> poly1_terms = poly1.polynomial;
           ArrayList<PolyTerm> poly2_terms = poly2.polynomial;
            
          
             for(int i=0;i<poly1_terms.size();i++)
             {
               for(int j=0;j<poly2_terms.size();j++)
               {
                 int key = poly1_terms.get(i).pow + poly2_terms.get(j).pow;
                 double value = poly1_terms.get(i).coeff*poly2_terms.get(j).coeff;
                // System.out.println(key+" "+value);
                 if(multi.containsKey(key))
                     {
                       multi.put(key , multi.get(key)+value);
                     }
                 else multi.put(key , value);
               }
             }
             for (Map.Entry<Integer, Double> entry : multi.entrySet()) 
               result.add(entry.getValue(),entry.getKey());
             return result;

         }
        
              public String toString()
              {
                     String output = "";
                     double coeff;
                     int pow;
                     ArrayList<PolyTerm> polynomial = this.polynomial;
                     for(int i=0; i<polynomial.size(); i++)
                     {
                           coeff = polynomial.get(i).coeff;
                           pow = polynomial.get(i).pow;
                           if(pow == 0)
                           {
                                  if(coeff<0)
                                         output += coeff+" ";
                                  else
                                         output += "+ "+coeff+" ";
                           }
                           else
                           {
                                  if(coeff<0)
                                         output += coeff+"(x^"+pow+") ";
                                  else
                                         output += "+ "+coeff+"(x^"+pow+") ";
                           }
                     }
                     return output;
       }

       static Polynomial addition(Polynomial poly1, Polynomial poly2) 
       {
        HashMap<Integer,Double> multi = new HashMap<Integer,Double>();
        Polynomial result = new Polynomial();

        ArrayList<PolyTerm> poly1_terms = poly1.polynomial;
        ArrayList<PolyTerm> poly2_terms = poly2.polynomial;
         
       //int max = max_degree(poly1_terms,poly2_terms);
          for(int i=0;i<poly1_terms.size();i++)
          {
                int key =  poly1_terms.get(i).pow;
              double value = poly1_terms.get(i).coeff;
               multi.put(key ,value);
          }
          for(int i=0;i<poly2_terms.size();i++)
          {
                int key =  poly2_terms.get(i).pow;
              double value = poly2_terms.get(i).coeff;
              if(multi.containsKey(key))
              {
                multi.put(key , multi.get(key)+value);
              }
              else multi.put(key ,value);
            }
          for (Map.Entry<Integer, Double> entry : multi.entrySet()) 
               result.add(entry.getValue(),entry.getKey());
          return result;
       }
       
       static int max_degree(ArrayList<PolyTerm> a,ArrayList<PolyTerm> b)
       {
              return a.get(0).pow>b.get(0).pow?a.get(0).pow:b.get(0).pow;
       }
       
       static Polynomial subtract(Polynomial poly1, Polynomial poly2) 
       {
              HashMap<Integer,Double> multi = new HashMap<Integer,Double>();
        Polynomial result = new Polynomial();

        ArrayList<PolyTerm> poly1_terms = poly1.polynomial;
        ArrayList<PolyTerm> poly2_terms = poly2.polynomial;
         
       for(int i=0;i<poly1_terms.size();i++)
          {
                int key =  poly1_terms.get(i).pow;
              double value = poly1_terms.get(i).coeff;
               multi.put(key ,value);
          }
       for(int i=0;i<poly2_terms.size();i++)
          {
                int key =  poly2_terms.get(i).pow;
              double value = poly2_terms.get(i).coeff;
              if(multi.containsKey(key))
              {
                multi.put(key , multi.get(key)-value);
              }
              else multi.put(key ,-value);
          }
       for (Map.Entry<Integer, Double> entry : multi.entrySet()) 
                     result.add(entry.getValue(),entry.getKey());
       return result;
       }



}


public class polyOps {
       public static void main(String args[]){
              String poly1 = "4x^6+3x^2+5x^1+2";
              String poly2 = "-5x^1+1";
              Polynomial p1 = new Polynomial();
              p1.makePolynomial(poly1);
              Polynomial p2 = new Polynomial();
              p2.makePolynomial(poly2);
              Polynomial add = Polynomial.addition(p1,p2);
              System.out.println((add.toString()).substring(1));
       }

}

