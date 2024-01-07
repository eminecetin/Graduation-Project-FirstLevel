import {useMemo} from "react";

import {useQuery, useMutation, queryClient} from "react-query";
import { fetchFlightList, deleteFlight } from "../../../api";

import {Link} from "react-router-dom";
import {Text, Button} from '@chakra-ui/react';
import {Table, Popconfirm, Flex} from "antd";

function Flights() {
    const queryClient = useQueryClient();
    const {isLoading, isError, data, error}=useQuery(
        'admin:flights', 
        fetchFlightList
        );

        const deleteMutation=useMutation(deleteFlight, {
            //refetchQueries: ["admin:flights"],
            onSuccess: () => queryClient.invalidateQueries("admin:flights")
        });

        const columns= useMemo(()=>{
            return [
                {
                    title: "Title",
                    dataIndex: "title",
                    key: "title"
                }, {
                    title: "Price",
                    dataIndex: "price",
                    key: "price",
                },
                {
                    title: "Created At",
                    dataIndex: "createdAt",
                    key: "createdAt"
                },
                {
                    title: "Action",
                    key: "action",
                    render: (text, record)=> (
                       <>
                         <Link to= {`/admin/flights/${MediaRecorder._id}`}>Edit</Link>
                         <Popconfirm
                         title= "Are you sure?"
                         onConfirm={()=>{
                            deleteMutation.mutate(record._id, {
                                onSuccess: () =>{
                                    console.log("success");
                                },
                            });
                            }}
                         onCancel={()=> console.log("iptal edildi")}
                         okText="Yes"
                         cancelText="No"
                         placement="left"
                        >
                            <a href="/#" style={{marginLeft: 10}}>Delete</a>
                        </Popconfirm>
    
                       </>                             
                        ),
                }
            ]
        }, []);

        if(isLoading){
            return <div>Loading...</div>
        }

        if(isError){
            return <div>Error {error.message}</div>
        }

        
    return <div>
        <Flex justify="space-between">
        <Text fontSize="2xl" p="5">
            Flights
            </Text>
            <Link to="/admin/flights/new"></Link>
        </Flex>
     
            <Table dataSource={dataSource} columns={columns} rowKey="_id"/>

    </div>;
}

export default Flights;