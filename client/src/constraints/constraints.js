import validate from "validate.js";

validate.validators.regex = function (value, options, key, attributes) {
  let regExp = new RegExp(options.pattern);

  if (!regExp.test(value)) {
    return options.message;
  }
};

const constraints = {
  firstName: {
    regex: {
      // Must be numbers followed by a name
      pattern: "[A-Za-z]",
      message: "can only contain letters",
    },
    presence: {
      allowEmpty: false,
    },
    type: "string",
  },

  lastName: {
    regex: {
      // Must be numbers followed by a name
      pattern: "[A-Za-z]",
      message: "can only contain letters",
    },
    presence: {
      allowEmpty: false,
    },
    type: "string",
  },

  emailAddress: {
    presence: {
      allowEmpty: false,
      message: "^Email field cannot be empty",
    },
    email: {
      message: "^E-mail address is invalid",
    },
    type: "string",
  },

  password: {
    presence: true,
    regex: {
      // Must be numbers followed by a name
      pattern:
        "^(?=.*d)(?=.*[a-z])(?=.*[!@#$%^&+=])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
      message:
        "^Password needs to be 8-26 characters long, 1 lower and uppercase letters, 1 number and 1 speical character",
    },
    length: {
      minimum: 8,
      maximum: 26,
    },
  },
};

export default constraints;
