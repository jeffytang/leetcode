package com.twq.queue.priority;

import java.util.*;

/**
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，
 * 关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。
 *
 * 你的设计需要支持以下的几个功能：
 *      1. postTweet(userId, tweetId): 创建一条新的推文
 *      2. getNewsFeed(userId): 检索最近的十条推文。
 *      每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 *      3. follow(followerId, followeeId): 关注一个用户
 *      4. unfollow(followerId, followeeId): 取消关注一个用户
 *
 * 示例：
 *
 * Twitter twitter = new Twitter();
 *
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 *
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 *
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 *
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 *
 * 链接：https://leetcode-cn.com/problems/design-twitter
 */
public class _355_DesignTwitter {
}

class Twitter {

    private Map<Integer, Tweet> twitter;
    private Map<Integer, Set<Integer>> followings;

    private PriorityQueue<Tweet> maxHeap;

    private long ts;

    /** Initialize your data structure here. */
    public Twitter() {
        twitter = new HashMap<>();
        followings = new HashMap<>();
        maxHeap = new PriorityQueue<>((t1, t2) -> (int) (t2.timestamp - t1.timestamp));
        ts = System.currentTimeMillis();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (twitter.containsKey(userId)) {
            Tweet oldHead = twitter.get(userId);
            Tweet newHead = new Tweet(tweetId, ts++);
            newHead.next = oldHead;
            twitter.put(userId, newHead);
        } else {
            twitter.put(userId, new Tweet(tweetId, ts++));
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        // 由于是全局使用的，使用之前需要清空
        maxHeap.clear();

        // 如果自己发了推文也要算上
        if (twitter.containsKey(userId)) {
            maxHeap.offer(twitter.get(userId));
        }

        // 关注人的推文
        Set<Integer> followingList = followings.get(userId);
        if (followingList != null) {
            for (Integer followingId : followingList) {
                Tweet tweet = twitter.get(followingId);
                if (tweet != null) {
                    maxHeap.offer(tweet);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            Tweet head = maxHeap.poll();
            res.add(head.id);

            if (head.next != null) {
                maxHeap.offer(head.next);
            }

            count++;
        }

        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followeeId == followerId)
            return;

        Set<Integer> followingList = followings.get(followerId);
        if (followingList == null) {
            Set<Integer> init = new HashSet<>();
            init.add(followeeId);
            followings.put(followerId, init);
        } else {
            if (followingList.contains(followeeId))
                return;
            followingList.add(followeeId);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId)
            return;

        Set<Integer> followingList = followings.get(followerId);
        if (followingList == null)
            return;

        followingList.remove(followeeId);
    }
}

// 推文类
class Tweet {
    int id; // 推文 id
    // 发推文的时间
    long timestamp;
    // 用户发的下一条推文
    Tweet next;

    public Tweet(int id, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }
}
