package be.voedsaam.vzw.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IMapper<B, D>{
	
	D mapToDTO(B b);
	
	B mapToObj(D d);
	
//	List<D> mapToDTO(List<B> bs);
//	
//	List<B> mapToObj(List<D> ds);
	
	default Collection<D> mapToDTO(List<B> bs) {
		List<D> ds = new ArrayList<D>();
		bs.stream().forEach(b -> ds.add(mapToDTO(b)));
		return ds;
	}

	default Collection<B> mapToObj(List<D> ds) {
		List<B> bs = new ArrayList<B>();
		ds.stream().forEach(d -> bs.add(mapToObj(d)));
		return bs;
	}

}
