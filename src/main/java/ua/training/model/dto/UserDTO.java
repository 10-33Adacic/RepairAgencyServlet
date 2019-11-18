package ua.training.model.dto;

import ua.training.model.entity.Role;

public class UserDTO {
    String email;
    String name;
    String password;
    Role role;

    public static Builder builder() {
        return new UserDTO().new Builder();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public class Builder {
        private Builder() {
        }


        public Builder email(String email) {
            UserDTO.this.email = email;
            return this;
        }

        public Builder password(String password) {
            UserDTO.this.password = password;
            return this;
        }

        public Builder role(Role role) {
            UserDTO.this.role = role;
            return this;
        }


        public Builder name(String name) {
            UserDTO.this.name = name;
            return this;
        }

        public UserDTO build() {
            return UserDTO.this;
        }
    }
}
