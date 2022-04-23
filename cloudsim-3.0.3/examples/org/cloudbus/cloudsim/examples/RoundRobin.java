package org.cloudbus.cloudsim.examples;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerSpaceShared;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.lists.HostList;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
public class RoundRobin
{
private static List<Cloudlet> cloudletList;

/** The vmList. */
private static List<Vm> vmList;

private static List<Vm> createVM(int userId, int vms, int idShift) {
	//Creates a container to store VMs. This list is passed to the broker later
	LinkedList<Vm> list = new LinkedList<Vm>();

	//VM Parameters
	long size = 10000; //image size (MB)
	int ram = 512; //vm memory (MB)
	//int mips =250;
	long bw = 1000;
	int pesNumber = 1 ; //number of cpus
	String vmm = "Xen"; //VMM name

	//create VMs
	Vm[] vm = new Vm[vms];

	
		vm[0] = new Vm(0, userId, 20, pesNumber, ram, bw, 1000, vmm, new CloudletSchedulerTimeShared());//creates a new vm characteristics object
		list.add(vm[0]);
		vm[1] = new Vm(1, userId,20, pesNumber, ram, bw, 1000, vmm, new CloudletSchedulerTimeShared());//creates a new vm characteristics object
		list.add(vm[1]);
		vm[2] = new Vm(2, userId,20, pesNumber, ram, bw, 1000, vmm, new CloudletSchedulerTimeShared());//creates a new vm characteristics object
		list.add(vm[2]);
		vm[3] = new Vm(3, userId,20, pesNumber, ram, bw, 1000, vmm, new CloudletSchedulerTimeShared());//creates a new vm characteristics object
		list.add(vm[3]);
		vm[4] = new Vm(4, userId,20, pesNumber, ram, bw, 1000, vmm, new CloudletSchedulerTimeShared());//creates a new vm characteristics object
		list.add(vm[4]);
	return list;
}


private static List<Cloudlet> createCloudlet(int userId, int cloudlets, int idShift){
	// Creates a container to store Cloudlets
	LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();

	//cloudlet parameters
	//long length = 1;
	long fileSize = 300;
	long outputSize = 300;
	int pesNumber = 1;
	UtilizationModel utilizationModel = new UtilizationModelFull();

	Cloudlet[] cloudlet = new Cloudlet[cloudlets];

	
		cloudlet[0] = new Cloudlet(0, 60, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[0].setUserId(userId);
		list.add(cloudlet[0]);
		
		cloudlet[1] = new Cloudlet(1, 60, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[1].setUserId(userId);
		list.add(cloudlet[1]);
		
		cloudlet[2] = new Cloudlet(2, 60, pesNumber,fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[2].setUserId(userId);
		list.add(cloudlet[2]);
		
		cloudlet[3] = new Cloudlet(3, 60, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[3].setUserId(userId);
		list.add(cloudlet[3]);
		
		cloudlet[4] = new Cloudlet(4, 60, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[4].setUserId(userId);
		list.add(cloudlet[4]);
		
		cloudlet[5] = new Cloudlet(5, 60, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[5].setUserId(userId);
		list.add(cloudlet[5]);
		
		cloudlet[6] = new Cloudlet(6, 60, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[6].setUserId(userId);
		list.add(cloudlet[6]);
		
		cloudlet[7] = new Cloudlet(7, 60, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[7].setUserId(userId);
		list.add(cloudlet[7]);
		
		cloudlet[8] = new Cloudlet(8, 60, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[8].setUserId(userId);
		list.add(cloudlet[8]);
		
		cloudlet[9] = new Cloudlet(9, 60, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
		cloudlet[9].setUserId(userId);
		list.add(cloudlet[9]);
		
		
		
		
		
	return list;
}


/**
 * Creates main() to run this example
 */
public static void main(String[] args) {
	Log.printLine("Starting CloudSimExample8...");

	try {
		// First step: Initialize the CloudSim package. It should be called
		// before creating any entities.
		int num_user = 2;   // number of grid users
		Calendar calendar = Calendar.getInstance();
		boolean trace_flag = false;  // mean trace events

		// Initialize the CloudSim library
		CloudSim.init(num_user, calendar, trace_flag);

		//GlobalBroker globalBroker = new GlobalBroker("GlobalBroker");

		// Second step: Create Datacenters
		//Datacenters are the resource providers in CloudSim. We need at list one of them to run a CloudSim simulation
		Datacenter datacenter0 = createDatacenter("Datacenter_0");
		Datacenter datacenter1 = createDatacenter("Datacenter_1");

		//Third step: Create Broker
		DatacenterBroker broker = createBroker("Broker_0");
		int brokerId = broker.getId();

		//Fourth step: Create VMs and Cloudlets and send them to broker
		vmList = createVM(brokerId, 5, 0); //creating 5 vms
		cloudletList = createCloudlet(brokerId, 10, 0); // creating 10 cloudlets

		broker.submitVmList(vmList);
		broker.submitCloudletList(cloudletList);

		// Fifth step: Starts the simulation
		CloudSim.startSimulation();

		// Final step: Print results when simulation is over
		List<Cloudlet> newList = broker.getCloudletReceivedList();//gets the cloudlet received list
		//newList.addAll(globalBroker.getBroker().getCloudletReceivedList());

		CloudSim.stopSimulation();

		printCloudletList(newList);

		//Print the debt of each user to each datacenter
		//datacenter0.printDebts();
		//datacenter1.printDebts();
		

		Log.printLine(RoundRobin.class.getName() + " finished!");
	}
	catch (Exception e)
	{
		e.printStackTrace();
		Log.printLine("The simulation has been terminated due to an unexpected error");
	}
}

private static Datacenter createDatacenter(String name){

	// Here are the steps needed to create a PowerDatacenter:
	// 1. We need to create a list to store one or more
	//    Machines
	List<Host> hostList = new ArrayList<Host>();

	// 2. A Machine contains one or more PEs or CPUs/Cores. Therefore, should
	//    create a list to store these PEs before creating
	//    a Machine.
	List<Pe> peList1 = new ArrayList<Pe>();

	int mips = 1000;

	// 3. Create PEs and add these into the list.
	//for a quad-core machine, a list of 4 PEs is required:
	peList1.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
	peList1.add(new Pe(1, new PeProvisionerSimple(mips)));
	peList1.add(new Pe(2, new PeProvisionerSimple(mips)));
	peList1.add(new Pe(3, new PeProvisionerSimple(mips)));

	//Another list, for a dual-core machine
	List<Pe> peList2 = new ArrayList<Pe>();

	peList2.add(new Pe(0, new PeProvisionerSimple(mips)));
	peList2.add(new Pe(1, new PeProvisionerSimple(mips)));

	//4. Create Hosts with its id and list of PEs and add them to the list of machines
	int hostId=0;
	int ram = 16384; //host memory (MB)
	long storage = 1000000; //host storage
	int bw = 10000;

	hostList.add(
			new Host(
				hostId,
				new RamProvisionerSimple(ram),
				new BwProvisionerSimple(bw),
				storage,
				peList1,
				new VmSchedulerTimeShared(peList1)
			)
		); // This is our first machine

	hostId++;

	hostList.add(
			new Host(
				hostId,
				new RamProvisionerSimple(ram),
				new BwProvisionerSimple(bw),
				storage,
				peList2,
				new VmSchedulerTimeShared(peList2)
			)
		); // Second machine

	// 5. Create a DatacenterCharacteristics object that stores the
	//    properties of a data center: architecture, OS, list of
	//    Machines, allocation policy: time- or space-shared, time zone
	//    and its price (G$/Pe time unit).
	String arch = "x86";      // system architecture
	String os = "Windows";          // operating system
	String vmm = "Xen";
	double time_zone = 10.0;         // time zone this resource located
	double cost = 3.0;              // the cost of using processing in this resource
	double costPerMem = 0.05;		// the cost of using memory in this resource
	double costPerStorage = 0.1;	// the cost of using storage in this resource
	double costPerBw = 0.1;			// the cost of using bw in this resource
	LinkedList<Storage> storageList = new LinkedList<Storage>();	//we are not adding SAN devices by now

	DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
            arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);


	// 6. Finally, we need to create a PowerDatacenter object.
	Datacenter datacenter = null;
	try {
		VmAllocationPolicySimple vm_policy = new VmAllocationPolicySimple(hostList);
		datacenter = new Datacenter(name, characteristics, vm_policy, storageList, 0);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return datacenter;
}


public static class VmAllocationPolicyMinimum extends org.cloudbus.cloudsim.VmAllocationPolicy {
//implement our on algorithms for deciding which host a new VM should be placed on
	private Map<String, Host> vm_table = new HashMap<String, Host>();
	
	private final Hosts hosts;
	private Datacenter datacenter;

	public VmAllocationPolicyMinimum(List<? extends Host> list) {
		super(list);
		hosts = new Hosts(list);
	}
	
	public void setDatacenter(Datacenter datacenter) {
		this.datacenter = datacenter;
	}
	
	public Datacenter getDatacenter() {
		return datacenter;
	}

	@Override
	public boolean allocateHostForVm(Vm vm) {
//allocates a host for a given vm
		if (this.vm_table.containsKey(vm.getUid()))
			return true;

		boolean vm_allocated = false;
		int tries = 0;
		
		do 
		{
			
			Host host = this.hosts.getWithMinimumNumberOfPesEquals(vm.getNumberOfPes());
			Log.printLine("\n hello");
			vm_allocated = this.allocateHostForVm(vm, host);//allocates specified host for a given vm
			
		} while (!vm_allocated && tries++ < hosts.size());
		

		return vm_allocated;
	}
	
	

	@Override
	public boolean allocateHostForVm(Vm vm, Host host) 
	{
		if (host != null && host.vmCreate(vm)) //allocates PEs amd memory to a new VM in the host
		{
			vm_table.put(vm.getUid(), host);//Associates the specified value with the specified key in this map. If the map previously contained a mapping for the key, the old value is replaced by the specified value.  
			Log.formatLine("%.4f: VM #" + vm.getId() + " has been allocated to the host#" + host.getId() + 
					" datacenter #" + host.getDatacenter().getId() + "(" + host.getDatacenter().getName() + ") #", 
					CloudSim.clock());
					
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> optimizeAllocation(List<? extends Vm> vmList) {
		return null;
	}

	@Override
	public void deallocateHostForVm(Vm vm) {
		Host host = this.vm_table.remove(vm.getUid());
		
		if (host != null)
		{
			host.vmDestroy(vm);//destroys a vm running in the host
		}
	}

	@Override
	public Host getHost(Vm vm) {
		return this.vm_table.get(vm.getUid());
	}

	@Override
	public Host getHost(int vmId, int userId) {
		return this.vm_table.get(Vm.getUid(userId, vmId));
	}
}


private static DatacenterBroker createBroker(String name) throws Exception{
	 return new DatacenterBroker(name);
}
private static void printCloudletList(List<Cloudlet> list) {
	int size = list.size();
	Cloudlet cloudlet;

	String indent = "    ";
	Log.printLine();
	Log.printLine("========== OUTPUT ==========");
	Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
			"Data center ID" + indent + "VM ID" + indent + indent + "Time" + indent + "Start Time" + indent + "Finish Time");

	DecimalFormat dft = new DecimalFormat("###.##");
	double total_time=0.0;
	double maxFT=0.0;
	for (int i = 0; i < size; i++) {
		cloudlet = list.get(i);
		total_time+=list.get(i).getFinishTime();
		double currentFT=list.get(i).getFinishTime();
		if(currentFT>maxFT)maxFT=currentFT;
		Log.print(indent + cloudlet.getCloudletId() + indent + indent);

		if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS){
			Log.print("SUCCESS");

			Log.printLine( indent + indent + cloudlet.getResourceId() + indent + indent + indent + cloudlet.getVmId() +
					indent + indent + indent + dft.format(cloudlet.getActualCPUTime()) +
					indent + indent + dft.format(cloudlet.getExecStartTime())+ indent + indent + indent + dft.format(cloudlet.getFinishTime()));
		}
	}
	double avgTAT=total_time/size;
	double throughPut=size/maxFT;
	Log.printLine("Average Turn Around Time is: "+avgTAT);
	Log.printLine("Average ThroughPut is: "+throughPut);
}
}