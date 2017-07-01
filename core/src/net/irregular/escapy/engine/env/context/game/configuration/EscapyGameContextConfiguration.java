package net.irregular.escapy.engine.env.context.game.configuration;



import net.irregular.escapy.engine.env.context.annotation.EscapyAPI;
import net.irregular.escapy.engine.env.context.annotation.meta.AnnotationProcessor;



/**
 * @author Henry on 28/06/17.
 */
@EscapyAPI // TODO: 30/06/17 MORE CONFIGURATIONS
public abstract class EscapyGameContextConfiguration {


	public AnnotationProcessor getAnnotationProcessor() {
		return new AnnotationProcessor();
	}


}
