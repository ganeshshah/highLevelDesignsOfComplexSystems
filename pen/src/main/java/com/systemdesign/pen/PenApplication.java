package com.systemdesign.pen;

import com.systemdesign.pen.models.nib.NibType;
import com.systemdesign.pen.models.pens.*;
import com.systemdesign.pen.models.refils.InkColour;
import com.systemdesign.pen.models.refils.RefilType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PenApplication {

	public static void main(String[] args) throws Exception {
		//SpringApplication.run(PenApplication.class, args);

		System.out.println("hello");

		Pen pen1 = new PenBuilder().get().setPenType(PenType.BALL).
				 setBrand(Brand.CLASSMATE).setInkColour(InkColour.GREEN)
				.setNib(0.25,NibType.BALL).setRefill(RefilType.BALL).build();

		Pen pen2 = new PenBuilder().get().setPenType(PenType.GEL).
				setBrand(Brand.CLASSMATE).setInkColour(InkColour.RED)
				.setNib(0.25,NibType.GEL).setRefill(RefilType.GEL).build();


	}

}
