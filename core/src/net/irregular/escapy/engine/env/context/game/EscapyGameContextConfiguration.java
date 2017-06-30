package net.irregular.escapy.engine.env.context.game;


import net.irregular.escapy.engine.env.context.annotation.meta.AnnotationProcessor;


/**
 * @author Henry on 28/06/17.
 */
public class EscapyGameContextConfiguration {


	public AnnotationProcessor getAnnotationProcessor() {
		return new AnnotationProcessor();
	}


}
