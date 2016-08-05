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
        		System.out.println(term);
        		int i=-1;
        		int coeff=0;
        		int power=0;
        		i = term.indexOf("x^"); 
        		System.out.println(i);
        		if(i!=-1){
        			System.out.println(term.substring(0,i-1));
        			coeff = Integer.parseInt(term.substring(0,i-1));
        			int indexOfTermInTerms = Poly.indexOf(term); 
        			if(indexOfTermInTerms != 0){
        				if(Poly.charAt(indexOfTermInTerms-1)=='-'){
        					coeff = -coeff;
        				}
        			}
        			power = Integer.parseInt(term.substring(i+2,term.length()));
        		}
        		else{
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
        		System.out.println("coeff"+coeff+" power"+power);
        	}
        }
        public static Polynomial multiply(Polynomial poly1,Polynomial poly2)
        {
           HashMap<Integer,Double> multi = new HashMap();
           Polynomial result = new Polynomial();

           ArrayList<PolyTerm> poly1_terms = poly1.polynomial;
           ArrayList<PolyTerm> poly2_terms = poly2.polynomial;
            
          
             for(int i=0;i<poly1_terms.size();i++)
             {
               for(int j=0;j<poly2_terms.size();j++)
               {
                 int key=poly1_terms.get(i).pow*poly2_terms.get(j).pow;
                 double value=poly1_terms.get(i).coeff*poly2_terms.get(j).coeff;
                 if(multi.containsKey(key))
                     {
                       multi.put(key , multi.get(key)+value);
                     }
                 else multi.put(key , value);
               }
             }
             for (Map.Entry<Integer, Double> entry : multi.entrySet()) 
             {
                 
                 result.add(entry.getValue(),entry.getKey());
                   //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
               }
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

	static Polynomial addition(Polynomial a, Polynomial b) {
		// TODO Auto-generated method stub
		
		Polynomial result = new Polynomial();
		PolyTerm polyterm1,polyterm2;
		ArrayList<PolyTerm> polyterm_listA = a.polynomial;
		ArrayList<PolyTerm> polyterm_listB = b.polynomial;
		
		for(int i=0; i<a.polynomial.size();i++)
		{
			polyterm1 = polyterm_listA.get(i);
			polyterm2 = polyterm_listB.get(i);
			
			result.add(polyterm1.coeff + polyterm2.coeff,polyterm1.pow);
		}
		
		return result;
	}
	
	static Polynomial subtract(Polynomial a, Polynomial b) {
		// TODO Auto-generated method stub
		Polynomial result = new Polynomial();
		PolyTerm polyterm1,polyterm2;
		ArrayList<PolyTerm> polyterm_listA = a.polynomial;
		ArrayList<PolyTerm> polyterm_listB = b.polynomial;
		
		for(int i=0; i<a.polynomial.size();i++)
		{
			polyterm1 = polyterm_listA.get(i);
			polyterm2 = polyterm_listB.get(i);
			
			result.add(polyterm1.coeff - polyterm2.coeff,polyterm1.pow);
		}
		
		return result;
	}



}


public class polyOps {
	public static void main(String args[]){
		String poly = "3x^2+5x^1+2";
		Polynomial p1 = new Polynomial();
		p1.makePolynomial(poly);
	}

}
