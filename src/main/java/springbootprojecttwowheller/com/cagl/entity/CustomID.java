package springbootprojecttwowheller.com.cagl.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomID implements IdentifierGenerator{
	
	public int generatedId() {
		Random random=new Random();
		return random.nextInt(101);
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		LocalDateTime time=LocalDateTime.now();
		
		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
        String formatDateTime2 = time.format(format2);  
		return formatDateTime2;
	}
}
