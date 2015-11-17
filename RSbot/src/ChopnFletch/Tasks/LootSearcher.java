package ChopnFletch.Tasks;

import org.powerbot.script.Filter;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;

import ChopnFletch.ChopnFletch;
import ChopnFletch.Enums.Nest;


public class LootSearcher extends Task<ClientContext>{

	ClientContext ctx;
	
	public LootSearcher(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ChopnFletch.status.equals("Chopping");
	}

	@Override
	public void execute() {
		
		ctx.groundItems.select(new Filter<GroundItem>() {

			@Override
			public boolean accept(GroundItem i) {
				for(Nest n: Nest.values()) {
					if(i.id() == n.getId()) {
						i.click();
					}
				}
				
				return false;
			}
		});
	}
}
