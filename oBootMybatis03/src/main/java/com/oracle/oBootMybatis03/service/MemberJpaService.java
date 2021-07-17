package com.oracle.oBootMybatis03.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootMybatis03.dao.MemberJpaRepository;
import com.oracle.oBootMybatis03.dao.MemberJpaRepositoryImpl;
import com.oracle.oBootMybatis03.domain.Member;

@Transactional
public class MemberJpaService {
	private final MemberJpaRepository memberJpaRepository;

	public MemberJpaService(MemberJpaRepository memberJpaRepository) {
		this.memberJpaRepository = memberJpaRepository;
	}
	
	// 회원가입
	public Long join(Member member) {
		System.out.println("MemberJpaService join member.getId()->"+member.getId());
		memberJpaRepository.save(member);
		return member.getId();	
	}
	
	public List<Member> getListAllMember() {
		List<Member> listMember = memberJpaRepository.findAll();
		System.out.println("MemberJpaService getListAllMember listMember.size()->"+listMember.size());
		return listMember;
	}
	
	//회원 상세 조회
	   public Optional<Member> findById(Long memberId){
	      Optional<Member> member = memberJpaRepository.findById(memberId);
	      return member;	      
	   }
	   
	// 회원 수정
	   public void memberUpdate(Member member) {
		   System.out.println("MemberJpaService memberUpdate member.getName()->"+member.getName());
		   System.out.println("MemberJpaService memberUpdate member.getId()->"+member.getId());
		   memberJpaRepository.updateByMember(member);
		   return;
	   }

}
