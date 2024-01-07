// AdminSignin.js
import React from "react";
import { Flex, Box, Heading, FormControl, FormLabel, Input, Button, Alert } from "@chakra-ui/react";
import adminValidationSchema from "./validation"; // Admin için ayrı bir validasyon şeması
import { fetchAdminLogin } from "../../../api"; // Admin girişi için özel bir API fonksiyonu
import { useFormik } from "formik";
import { useAuth } from "../../../contexts/AuthContext"; // Eğer farklı bir context kullanılacaksa burayı güncelleyin

function AdminSignin({ history }) {
    const { login } = useAuth(); // Eğer farklı bir auth metodu kullanılacaksa burayı güncelleyin
    const formik = useFormik({
        initialValues: {
            email: "",
            password: "",
        },
        validationSchema: adminValidationSchema,
        onSubmit: async (values, bag) => {
            try {
                const loginResponse = await fetchAdminLogin({ // Admin girişi için özel API
                    email: values.email,
                    password: values.password
                });
                login(loginResponse); // Giriş yapan kullanıcıyı sisteme dahil etmek

                history.push('/admin/dashboard') // Admin dashboard sayfasına yönlendirme
                console.log(loginResponse);
            } catch (e) {
                bag.setErrors({ general: e.response.data.message });
            }
        },
    });

    return (
        <div>
            <Flex align="center" width="full" justifyContent="center">
                <Box pt={10}>
                    <Box textAlign="center">
                        <Heading>Admin Sign In</Heading>
                    </Box>
                    <Box my={5}>{
                        formik.errors.general && (
                            <Alert status="error">{formik.errors.general}</Alert>
                        )
                    }
                    </Box>
                    <Box my={5} textAlign="left">
                        <form onSubmit={formik.handleSubmit}>
                            <FormControl>
                                <FormLabel>E-mail</FormLabel>
                                <Input 
                                    name="email"
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    value={formik.values.email}
                                    isInvalid={formik.touched.email && formik.errors.email}
                                />
                            </FormControl>

                            <FormControl mt="4">
                                <FormLabel>Password</FormLabel>
                                <Input 
                                    name="password" 
                                    type="password"
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    value={formik.values.password}
                                    isInvalid={formik.touched.password && formik.errors.password}
                                />
                            </FormControl>
                            <Button mt="4" width="full" type="submit">
                                Sign In
                            </Button>
                        </form>
                    </Box>
                </Box>
            </Flex>
        </div>
    );
}

export default AdminSignin;