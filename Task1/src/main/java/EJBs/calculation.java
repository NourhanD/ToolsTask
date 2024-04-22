package EJBs;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Stateless
@Entity
public class calculation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int Number1;
	private int Number2;
	private String Operation;
	private int result;
	
	public int getNumber1() {
		return Number1;
	}
	public void setNumber1(int number1) {
		Number1 = number1;
	}
	public int getNumber2() {
		return Number2;
	}
	public void setNumber2(int number2) {
		Number2 = number2;
	}
	public String getOperation() {
		return Operation;
	}
	public void setOperation(String operation) {
		Operation = operation;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	public calculation (int number1, int number2, String operation, int result) {
        this.Number1 = number1;
        this.Number2 = number2;
        this.Operation = operation;
        this.result = result;
    }
	
	public calculation () {}
	
	public int calcResult (int n1, int n2, String op)
	{
		int result = 0;
		switch(op) {
		case "+":
			result = n1 + n2;
            break;
        case "-":
            result = n1 - n2;
            break;
        case "*":
            result = n1 * n2;
            break;
        case "/":
        	if (n2 != 0) 
        	{
                result = n1 / n2;
            } 
        	else 
        	{
                throw new IllegalArgumentException("Division by zero is not allowed.");
            }
            break;
        default:
            throw new IllegalArgumentException("Invalid operation: " + op);
            }

    return result;
    }
}
