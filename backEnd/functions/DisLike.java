public class DisLike {
    public interface DisLike {

    void disLike(BlogLikeRequest request) throws BlogLikeException;

    boolean isDisLiked(BlogLikeRequest request) throws BlogLikeException;

    int getDisLikeCount(int blogId) throws BlogLikeException;
    }

    public class BlogLikeRequest {

        private final int blogId;
        private final int userId;
    
        public BlogLikeRequest(int blogId, int userId) {
            this.blogId = blogId;
            this.userId = userId;
        }
    
        public int getBlogId() {
            return blogId;
        }
    
        public int getUserId() {
            return userId;
        }
    }
    public class BlogDislikeServiceImpl implements BlogDislikeService {

        private final BlogDislikeRepository repository;
    
        public BlogDislikeServiceImpl(BlogDislikeRepository repository) {
            this.repository = repository;
        }
    
        @Override
        public void dislike(BlogLikeRequest request) throws BlogLikeException {
            try {
                repository.addDislike(request.getBlogId(), request.getUserId());
            } catch (DataAccessException e) {
                throw new BlogLikeException("Error adding dislike", e);
            }
        }
    
        
    }
    
    
}
