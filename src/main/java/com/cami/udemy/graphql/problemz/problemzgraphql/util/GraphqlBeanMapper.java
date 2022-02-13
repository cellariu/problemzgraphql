package com.cami.udemy.graphql.problemz.problemzgraphql.util;

import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Problemz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Solutionz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.Userz;
import com.cami.udemy.graphql.problemz.problemzgraphql.datasource.entity.UserzToken;
import com.cami.udemy.graphql.problemz.problemzgraphql.types.*;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GraphqlBeanMapper {

    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(1);

    public static User mapToGrapghql(Userz original) {
        var result = new User();

        result.setId(original.getId().toString());
        result.setUsername(original.getUsername());
        result.setEmail(original.getEmail());
        result.setDisplayName(original.getDisplayName());
        result.setAvatar(original.getAvatar());

        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        result.setCreateDateTime(createDateTime);

        return result;
    }

    public static Problem mapToGrapghql(Problemz original) {
        var result = new Problem();

        result.setId(original.getId().toString());
        result.setTitle(original.getTitle());
        result.setContent(original.getContent());

        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        result.setCreateDateTime(createDateTime);
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createDateTime));

        result.setSolutionCount(original.getSolutions().size());

        result.setTags(getTags(original.getTags()));

        result.setSolutions(
                original.getSolutions().stream()
                    .map(s -> mapToGrapghql(s))
                    .collect(Collectors.toList()));

        result.setAuthor(mapToGrapghql(original.getCreatedBy()));

        return result;
    }

    private static List<String> getTags(String concatenatedTags) {
        if (concatenatedTags == null || concatenatedTags.isBlank()) {
            return List.of();
        }

        return Arrays.stream(concatenatedTags.split(",")).collect(Collectors.toList());
    }

    public static Solution mapToGrapghql(Solutionz original) {
        var result = new Solution();

        result.setId(original.getId().toString());
        result.setContent(original.getContent());
        result.setCategory(SolutionCategory.valueOf(original.getCategory()));
        result.setVoteAsBadCount(original.getVoteBadCount());
        result.setVoteAsGoodCount(original.getVoteGoodCount());

        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);
        result.setCreateDateTime(createDateTime);
        result.setPrettyCreateDateTime(PRETTY_TIME.format(createDateTime));

        result.setCreatedBy(mapToGrapghql(original.getCreatedBy()));

        return result;
    }

    public static UserAuthToken mapToGrapghql(UserzToken original) {
        var result = new UserAuthToken();

        result.setId(original.getUserId().toString());
        result.setAuthToken(original.getAuthToken());
        result.setExpiryTime(original.getExpiryTimestamp().atOffset(ZONE_OFFSET));
        result.setCreateTimestamp(original.getCreateTimestamp().atOffset(ZONE_OFFSET));

        return result;
    }

}
