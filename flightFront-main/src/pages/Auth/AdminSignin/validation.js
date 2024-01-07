// adminValidation.js
import * as yup from "yup";

const adminValidations = yup.object().shape({
    email: yup
        .string()
        .email("Geçerli bir email giriniz")
        .required("Zorunlu alan..!"),
    password: yup
        .string()
        .min(5, "Parolanız en az 5 karakter olmalı..!")
        .required("Zorunlu alan"),
});

export default adminValidations;