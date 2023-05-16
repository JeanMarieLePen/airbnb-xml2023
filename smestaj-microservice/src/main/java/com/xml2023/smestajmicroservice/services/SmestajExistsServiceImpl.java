package com.xml2023.smestajmicroservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml2023.mainapp.DeleteSmestajsForHostRequest;
import com.xml2023.mainapp.DeleteSmestajsForHostResponse;
import com.xml2023.mainapp.RezervacijaExistsResponse;
import com.xml2023.mainapp.SmestajExistsRequest;
import com.xml2023.mainapp.SmestajExistsResponse;
import com.xml2023.mainapp.SmestajGrpcGrpc.SmestajGrpcImplBase;
import com.xml2023.mainapp.SmestajIdsForHostRequest;
import com.xml2023.mainapp.SmestajIdsForHostResponse;
import com.xml2023.mainapp.getListaSmestajaByUserIdRequest;
import com.xml2023.mainapp.getListaSmestajaByUserIdResponse;
import com.xml2023.mainapp.getSmestajByIdRequest;
import com.xml2023.mainapp.getSmestajByIdResponse;
import com.xml2023.smestajmicroservice.model.data.Smestaj;
import com.xml2023.smestajmicroservice.repositories.SmestajRep;

import io.grpc.stub.StreamObserver;

@Service
public class SmestajExistsServiceImpl extends SmestajGrpcImplBase{
	@Autowired SmestajRep sRep;
	
	@Override
	public void exists(SmestajExistsRequest request, StreamObserver<SmestajExistsResponse> responseObserver) {
		String id= request.getId();
		Smestaj r= sRep.findById(id).orElse(null);
		SmestajExistsResponse.Builder response = SmestajExistsResponse.newBuilder();
		if(r == null) {
			response.setResponseCode(104).setResponsemessage("INVALID ID");
			
		}else {
			response.setResponseCode(200).setResponsemessage(r.getId());
		}
		
		responseObserver.onNext(response.build());
		
		responseObserver.onCompleted();
//		super.greeting(request, responseObserver);
	
	}

	@Override
	public void getSmestajIdsForHost(SmestajIdsForHostRequest request,
			StreamObserver<SmestajIdsForHostResponse> responseObserver) {
		String hostId=request.getUserId();
		List<String> ids= sRep.findAll().stream().filter(x->x.getVlasnik().getId().equals(hostId)).map(x->x.getId()).collect(Collectors.toList());

		SmestajIdsForHostResponse.Builder res= SmestajIdsForHostResponse.newBuilder();
		if(ids.size()>0) {
			for(int i=0; i<ids.size();i++) {
				res.setSmestajIds(i, ids.get(i));
			}
		}else 	res.setSmestajIds(0, "0");

		responseObserver.onNext(res.build());
		
		responseObserver.onCompleted();
		
	}

	@Override
	public void deketeSnestajsForHost(DeleteSmestajsForHostRequest request,
			StreamObserver<DeleteSmestajsForHostResponse> responseObserver) {
		String hostId=request.getHostId();
		List<String> smestajiIds=sRep.findAll().stream().filter(x->x.getVlasnik().getId().equals(hostId)).map(x->x.getId()).collect(Collectors.toList());
		sRep.deleteAllById(smestajiIds);
		List<String> smestajiIdsCheck=sRep.findAll().stream().filter(x->x.getVlasnik().getId().equals(hostId)).map(x->x.getId()).collect(Collectors.toList());
		
		DeleteSmestajsForHostResponse.Builder res= DeleteSmestajsForHostResponse.newBuilder();
		if(smestajiIdsCheck.size()>0) {
			res.setSuccess(0);
		}else res.setSuccess(1);
		responseObserver.onNext(res.build());
		responseObserver.onCompleted();
	}

	@Override
	public void getSmestajById(getSmestajByIdRequest request, StreamObserver<getSmestajByIdResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.getSmestajById(request, responseObserver);
	}

	@Override
	public void getListaSmestajaByUserId(getListaSmestajaByUserIdRequest request,
			StreamObserver<getListaSmestajaByUserIdResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.getListaSmestajaByUserId(request, responseObserver);
	}
	
	
	/*
	 * @Override public void getSmestaj(GetSmestajiForHostRequest request,
	 * StreamObserver<ListaSmestajaResponse> responseObserver) { // TODO
	 * Auto-generated method stub String id = request.getUserId();
	 * ArrayList<Smestaj> lista = new ArrayList<Smestaj>(); lista =
	 * (ArrayList<Smestaj>) this.sRep.findAllByVlasnikId(id);
	 * ListaSmestajaResponse.Builder response = ListaSmestajaResponse.newBuilder();
	 * if(lista!= null && lista.size() > 0) {
	 * 
	 * for(int i = 0; i < lista.size(); i++) { SmestajDTO.Builder retVal =
	 * SmestajDTO.newBuilder(); AdresaDTO.Builder adrRetVal =
	 * AdresaDTO.newBuilder();
	 * adrRetVal.setAdresa(lista.get(i).getAdresa().getAdresa());
	 * adrRetVal.setId(lista.get(i).getAdresa().getId());
	 * adrRetVal.setLat(lista.get(i).getAdresa().getLat());
	 * adrRetVal.setLng(lista.get(i).getAdresa().getLng());
	 * retVal.setAdresa(adrRetVal);
	 * 
	 * Cenovnik.Builder cenRetVal = Cenovnik.newBuilder();
	 * cenRetVal.setCena(lista.get(i).getCenovnik().getCena());
	 * cenRetVal.setCenaLeto(lista.get(i).getCenovnik().getCenaLeto());
	 * cenRetVal.setCenaVikend(lista.get(i).getCenovnik().getCenaVikend());
	 * cenRetVal.setCenaPraznik(lista.get(i).getCenovnik().getCenaPraznik());
	 * retVal.setCenovnik(cenRetVal);
	 * 
	 * Pogodnosti.Builder pogodnosti = Pogodnosti.newBuilder(); for(Pogodnost p :
	 * lista.get(i).getPogodnosti()) { PogodnostDTO.Builder pdto =
	 * PogodnostDTO.newBuilder(); pdto.setNaziv(p.getNaziv());
	 * pdto.setId(p.getId()); pogodnosti.addPogodnosti(pdto); }
	 * retVal.setPogodnosti(pogodnosti);
	 * 
	 * for(byte[] img : lista.get(i).getSlike()) { slika.Builder slk =
	 * slika.newBuilder(); slk.setSlika(img.toString()); retVal.addSlike(slk); }
	 * 
	 * retVal.setMaxGosti(lista.get(i).getMaxGosti());
	 * retVal.setMinGosti(lista.get(i).getMinGosti());
	 * 
	 * response.setSmestajList(i, retVal); } } responseObserver.onCompleted(); }
	 */
}
