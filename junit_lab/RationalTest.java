import junit.framework.TestCase;

public class RationalTest extends TestCase {

    protected Rational HALF;

    protected void setUp() {
      HALF = new Rational( 1, 2 );
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
    }

    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));
    }

    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
    }
    

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }

    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
    
    /**** new tests ***/
    public void testDividedByZero(){
    	try{
    		Rational r = new Rational(2,0);
    	}catch(Exception e){
    		return;
    	}
    	fail("2/0 should not be valid");
    }
    
    public void testDividedByZero2(){
    	try{
    		Rational r1 = new Rational(2,1);
    		Rational r2 = new Rational(0,1);
    		r1.divides(r2);
    	}catch(Exception e){
    		return;
    	}
    	fail("2/0 should not be valid");
    }
    
    public void testMul(){
    	Rational r1 = new Rational(2,3);
    	Rational r2 = new Rational(3,4);
    	Rational r3 = new Rational(4,5);
    	Rational r4 = new Rational(-1,1);
    	assertEquals(new Rational(2,5),r1.times(r2).times(r3));
    	assertEquals(new Rational(-2,3),r1.times(r4));
    }
    
    public void testLess(){
    	Rational r1 = new Rational(-1,1);
    	Rational r2 = new Rational(1,-1);
    	Rational r3 = new Rational(2,3);
    	assertTrue(r1.isLessThan(r3));
    	assertTrue(r2.isLessThan(r3));
    }
    
    public void testToString(){
    	Rational r1 = new Rational(2,-3);
    	assertTrue(r1.toString().equals("-2/3"));
    }
    
    public void testAbs(){
    	Rational r1 = new Rational(-1,1);
    	Rational r2 = new Rational(1,-1);
    	Rational r3 = new Rational(-1,-1);
    	Rational r4 = new Rational(1,1);
    	assertEquals(r1.abs(),r2.abs());
    	assertEquals(r2.abs(),r3.abs());
    	assertEquals(r3.abs(),r4.abs());
    	assertEquals(r1.abs(),r4.abs());
    }
    
    public void testCopy(){
    	Rational r1 = new Rational(1,2);
    	Rational r2 = new Rational(r1);
    	assertEquals(r1,r2);
    }
    
    public void testEqual(){
    	Rational r1 = new Rational(-1,2);
    	Rational r2 = new Rational(1,-2);
    	assertEquals(r1,r2);
    }
    
    public void testPlus(){
    	Rational r1 = new Rational(-1,2);
    	Rational r2 = new Rational(1,3);
    	assertEquals(new Rational(1,-6), r1.plus(r2));
    }
    
    public void testMinus(){
    	Rational r1 = new Rational(-1,2);
    	Rational r2 = new Rational(1,3);
    	assertEquals(new Rational(5,-6), r1.minus(r2));
    }
    
    public void testInvalidRoot(){
    	try{
    		Rational r1 = new Rational(-2,1);
    		r1.root();
    	}catch(IllegalArgumentToSquareRootException e){
    		return;
    	}
    	fail("should throw exception on root -2");
    }
    
    
}
