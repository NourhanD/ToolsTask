package App;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import EJBs.calculation;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("cal")
public class calcService {
	
	@PersistenceContext(unitName="Calcs")
	private EntityManager entityManager;
	
	@POST
	@Path("calc")
	public String createCalculation(calculation  c) {
		int n1 = c.getNumber1();
		int n2 = c.getNumber2();
		String op = c.getOperation();
		
		int result = c.calcResult(n1, n2, op);
		c.setResult(result);
		
		 try {
	            entityManager.persist(c); 
	            return "Result: " + result;
	         } 
		 catch (Exception e)
		 {
	            e.printStackTrace();
	            return "Status: 500";
	            }
	}
	
	@GET
	@Path("calculations")
	public List<calculation> getAllCalculations() {
        try {
        
            TypedQuery<calculation> query = entityManager.createQuery("SELECT c FROM calculation c", calculation.class);
            List<calculation> calculations = query.getResultList();
            return calculations;
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
	}
}
