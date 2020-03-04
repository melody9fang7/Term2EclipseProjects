/**
 * This class contains class (static) methods that will help you test the
 * Picture class methods. Uncomment the methods and the code in the main to
 * test.
 * 
 * @author Barbara Ericson
 */
public class PictureTester {
	
	public static void testEncrypt2(){
		Picture main = new Picture("images/blue-mark.jpg");  //the original
		Picture message = new Picture("images/message.jpg"); //the message
		main.show();
		
	}
	

	public static void main(String[] args) {
		testEncrypt2();
		
	
	}
}