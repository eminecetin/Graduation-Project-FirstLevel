import * as yup from "yup";

const validations=yup.object().shape({
    email: yup
        .string()
        .email("Geçerli bir email giriniz")
        .required("Zorunlu alan..!"),
    password: yup
    .string()
    .min(5, "Paralonuz en az 5 karakter olmalı..!")
    .required(),
});

export default validations; 