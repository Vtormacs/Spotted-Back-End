package com.Rede_Social.DTO.Mapper;

import com.Rede_Social.DTO.Consulta.CommentDTO;
import com.Rede_Social.Entity.CommentEntity;
import com.Rede_Social.Repository.ComplaintRepository;
import com.Rede_Social.Repository.LikeRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class CommentDTOMapper {
    public static CommentDTO toCommentDto(CommentEntity commentEntity, UUID idUser, LikeRepository likeRepository, ComplaintRepository complaintRepository) {
        boolean isLiked = likeRepository.findByCommentAndUser(commentEntity.getUuid(), idUser).isPresent();
        boolean isReported = complaintRepository.findByCommentAndUser(commentEntity.getUuid(), idUser).isPresent();

        return new CommentDTO(
                commentEntity.getUuid(),
                commentEntity.getData(),
                isLiked,
                isReported,
                commentEntity.getConteudo(),
                commentEntity.getLikes() != null ? commentEntity.getLikes().size() : 0,
                commentEntity.getProfileAnimal(),
                commentEntity.getPost().getUuid()
        );
    }
}
