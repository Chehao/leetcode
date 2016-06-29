package chehao.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Twitter {
	
	public static class User{
		public int userId;
		public List<Integer> follow = new ArrayList<Integer>();
	}
	public static class UserTweet{
		public UserTweet(int userId2, int tweetId2) {
			this.userId = userId2;
			this.tweetId = tweetId2;
		}
		public int userId;
		public int tweetId;
	}
	Map<Integer,User> userMap = new HashMap<Integer,User>();
	
	List<UserTweet> list = new ArrayList<UserTweet>();
	
	/** Initialize your data structure here. */
	public Twitter() {

	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if(!userMap.containsKey(userId)){
			User user = new User();
			user.userId = userId;
			userMap.put(userId, user);
		}
		list.add(new UserTweet(userId,tweetId));
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item
	 * in the news feed must be posted by users who the user followed or by the
	 * user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		User user = userMap.get(userId);
		if(!userMap.containsKey(userId)){
			user = new User();
			user.userId = userId;
			userMap.put(userId, user);
		}
		final List<Integer> follows = user.follow;
		
		/*List<Integer> result = list.stream()
				.filter(t -> follows.contains(t.userId) || t.userId == userId )
				.map(t-> t.tweetId)
				.collect(Collectors.toList());
		List<Integer> r =  result.subList(result.size() > 10 ? result.size() - 10 : 0, result.size());
		Collections.reverse(r);*/
		List<Integer> r = new ArrayList<Integer>();
		int count = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			if(follows.contains(list.get(i).userId) ||list.get(i).userId == userId){
				r.add(list.get(i).tweetId);
				count++;
				if (count >= 10)
					break;
			}
		}
		return r;
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void follow(int followerId, int followeeId) {
		if(!userMap.containsKey(followerId)){
			User user = new User();
			user.userId = followerId;
			userMap.put(followerId, user);
		}
		userMap.get(followerId).follow.add(followeeId);
	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be
	 * a no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		if(!userMap.containsKey(followerId)){
			User user = new User();
			user.userId = followerId;
			userMap.put(followerId, user);
		}
		userMap.get(followerId).follow.remove(new Integer(followeeId));
	}
	
	public static void main(String[] args) {
		Twitter twitter = new Twitter();

		// User 1 posts a new tweet (id = 5).
		twitter.postTweet(1, 5);

		// User 1's news feed should return a list with 1 tweet id -> [5].
		System.out.println(twitter.getNewsFeed(1));

		// User 1 follows user 2.
		twitter.follow(1, 2);

		// User 2 posts a new tweet (id = 6).
		twitter.postTweet(2, 6);

		// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
		// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
		System.out.println(twitter.getNewsFeed(1));

		// User 1 unfollows user 2.
		twitter.unfollow(1, 2);

		// User 1's news feed should return a list with 1 tweet id -> [5],
		// since user 1 is no longer following user 2.
		System.out.println(twitter.getNewsFeed(1));
	}
}
