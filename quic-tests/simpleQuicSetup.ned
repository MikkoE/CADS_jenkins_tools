//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program.  If not, see http://www.gnu.org/licenses/.
//

//package inet.examples.quic.basic;

import inet.applications.netperfmeter.NetPerfMeterHost;
import inet.networklayer.configurator.ipv4.FlatNetworkConfigurator;
import inet.networklayer.configurator.ipv4.IPv4NetworkConfigurator;
import inet.node.inet.Router;
import inet.node.inet.StandardHost;

import ned.DatarateChannel;


@license(LGPL);

network QuickTest
{
    @display("bgb=797,414");
    types:
        channel pppline extends DatarateChannel
        {
            delay = 0.01ms;
            datarate = 12Mbps;
            per = 0;
            ber = 0;
        }
        channel ppplineBottleneck extends DatarateChannel
        {
            delay = 1ms;
            datarate = 10Mbps;
            per = 0;
            ber = 0;
        }
    submodules:
        router1: Router {
            @display("p=273,199");
            gates:
                pppg[2];
        }
        router2: Router {
            @display("p=503,199");
            gates:
                pppg[2];
        }
        configurator: IPv4NetworkConfigurator {
            @display("p=37,31");
        }
        host1: NetPerfMeterHost {
            @display("p=37,199");
            gates:
                pppg[1];
        }
        host2: NetPerfMeterHost {
            @display("p=737,199");
            gates:
                pppg[1];
        }
    connections:
        host1.pppg[0] <--> pppline <--> router1.pppg[0];
        router1.pppg[1] <--> ppplineBottleneck <--> router2.pppg[0];
        router2.pppg[1] <--> pppline <--> host2.pppg[0];
}
