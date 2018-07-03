package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.val;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.components.factory.FactoryModule;
import net.tindersamurai.escapy.map.node.EscapyNode;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.utils.EscapyUtils;

import javax.inject.Singleton;

@Module(componentsRootClass = StageModule.class,
		include = { ConfigModule.class, FactoryModule.class }
) public final class StageModule {


	@Provide @Singleton public Void test (
			EscapyComponentParser parser,
			StageContainer container
	) {

		val file = container.getDefaultStage().getDefaultLocation().getFile().getUrl();
		System.out.println(file);

		IEscapyNode root = parser.parseComponent(file);
		System.out.println(root.treeView());

		return EscapyUtils._void();
	}

}