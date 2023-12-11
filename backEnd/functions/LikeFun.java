public class LikeFun {
    public class Like {

        private int blogId;
        private int userId;
        private boolean isLiked;
    
        public Like(int blogId, int userId) {
            this.blogId = blogId;
            this.userId = userId;
            this.isLiked = false; 
        }
    
        public void like() {
           
            isLiked = true;
            System.out.println("User " + userId + " liked blog " + blogId);
        }
    
        public void unlike() {
           
            isLiked = false;
            System.out.println("User " + userId + " unliked blog " + blogId);
        }
    
        
    }
    
    
}
