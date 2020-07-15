package custom.maven.project.util;

public abstract class OfflineCause {
    protected final long timestamp = System.currentTimeMillis();
    
    /**
     * Taken offline by user.
     * @since 1.551
     */
    public static class UserCause extends SimpleOfflineCause {
        private final User user;

        public UserCause(User user, String message) {
            super(hudson.slaves.Messages._SlaveComputer_DisconnectedBy(
                    user!=null ? user.getId() : Jenkins.ANONYMOUS.getName(),
                    message != null ? " : " + message : ""
            ));
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    /**
     * Taken offline by user.
     * @since 1.551
     */
    public static class ModifiedFrom_UserCause extends SimpleOfflineCause {
        private final User user;

        public ModifiedFrom_UserCause(User user, String message) {
            super(hudson.slaves.Messages._SlaveComputer_DisconnectedBy(
                    user!=null ? user.getId() : Jenkins.ANONYMOUS.getName(),
                    message != null ? " : " + message : ""
            ));
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

}
