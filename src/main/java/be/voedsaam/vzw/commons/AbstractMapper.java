package be.voedsaam.vzw.commons;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<B, D> implements IMapper<B, D> {

	public List<D> mapToDTO(List<B> bs) {
		List<D> ds = new ArrayList<D>();
		bs.stream().forEach(b -> ds.add(mapToDTO(b)));
		return ds;
	}

	public List<B> mapToObj(List<D> ds) {
		List<B> bs = new ArrayList<B>();
		ds.stream().forEach(d -> bs.add(mapToObj(d)));
		return bs;
	}

}
